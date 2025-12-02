package com.livenne;

import com.livenne.annotation.*;
import com.livenne.utils.ObjectUtils;
import com.livenne.utils.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Slf4j
public class RouterFilter implements Filter {

    public static void register(ServletContext context){
        FilterRegistration.Dynamic registration = context.addFilter("RouterFilter",new RouterFilter());
        registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        if (req.getMethod().equals("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }
        for (Object controller : BeanFactory.controllerBeanList) {
            for (Field field : controller.getClass().getDeclaredFields()) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                field.setAccessible(true);
                if (field.getType().isAssignableFrom(HttpServletRequest.class)) {
                    field.set(controller, req);
                }
                if (field.getType().isAssignableFrom(HttpServletResponse.class)) {
                    field.set(controller, res);
                }
            }
            routerMatch(req,res,controller);
        }
    }

    @SneakyThrows
    private void routerMatch(HttpServletRequest req,HttpServletResponse res,Object controller){
        String apiHead = controller.getClass().getAnnotation(Controller.class).value();
        for (Method method : controller.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            String mapPath = apiHead;
            String mapMethod;
            if (method.isAnnotationPresent(GetMapping.class)){
                mapPath += method.getAnnotation(GetMapping.class).value();
                mapMethod = "GET";
            }else if (method.isAnnotationPresent(PostMapping.class)){
                mapPath += method.getAnnotation(PostMapping.class).value();
                mapMethod = "POST";
            }else {
                continue;
            }

            if (!mapMethod.equals(req.getMethod())) {
                continue;
            }
            if (!StringUtils.isPathEquals(req.getRequestURI(),mapPath)){
                continue;
            }
            Map<String, String> pathVarMap = StringUtils.pathVarMap(req.getRequestURI(), mapPath);
            List<Object> paramList = new ArrayList<>();
            for (Parameter parameter : method.getParameters()) {
                Object arg = null;
                Class<?> type = parameter.getType();
                if (parameter.isAnnotationPresent(PathVariable.class)){
                    String path = parameter.getAnnotation(PathVariable.class).value();
                    arg = StringUtils.toObject(pathVarMap.get(path), type);
                }else if (parameter.isAnnotationPresent(RequestParm.class)){
                    String parm =  parameter.getAnnotation(RequestParm.class).value();
                    arg = StringUtils.toObject(req.getParameter(parm),type);
                } else if (parameter.isAnnotationPresent(Attribute.class)) {
                    String attr = parameter.getAnnotation(Attribute.class).value();
                    arg = req.getAttribute(attr);
                } else if (parameter.isAnnotationPresent(RequestBody.class) && method.isAnnotationPresent(PostMapping.class)) {
                    arg = StringUtils.toObject(StringUtils.getBody(req),type);
                }
                paramList.add(arg);
            }
            Object ret;
            try{
                ret = method.invoke(controller, paramList.toArray());
            }catch (Exception e){
                Throwable cause = e;
                if (e instanceof InvocationTargetException) {
                    cause = e.getCause();
                    if (cause == null) cause = e;
                }
                ret = exceptionHandle((Exception) cause);
            }
            PrintWriter out = res.getWriter();
            out.println(StringUtils.toJson(ret));
        }
    }
    private Object exceptionHandle(Exception e){
        Object exHdl = null;
        Method hdl = null;
        for (Object exceptionHandler : BeanFactory.controllerAdviceBeanList) {
            for (Method handler : exceptionHandler.getClass().getDeclaredMethods()) {
                handler.setAccessible(true);
                if (!handler.isAnnotationPresent(ExceptionHandler.class)) {
                    continue;
                }
                Class<? extends Exception>[] matchException = handler.getAnnotation(ExceptionHandler.class).value();
                for (Class<? extends Exception> exception : matchException) {
                    if (e.getClass().equals(exception)) {
                        List<Object> paramList = new ArrayList<>();
                        for (Parameter parameter : handler.getParameters()) {
                            Class<?> type = parameter.getType();
                            if (type.equals(e.getClass())){
                                paramList.add(e);
                            }else {
                                paramList.add(ObjectUtils.getDefaultValue(type));
                            }
                        }
                        try {
                            return handler.invoke(exceptionHandler, paramList.toArray());
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            log.error(ex.getMessage(),ex);
                            return null;
                        }
                    }
                    if (exception.isAssignableFrom(e.getClass())) {
                        exHdl = exceptionHandler;
                        hdl = handler;
                    }
                }
            }
        }
        if (hdl == null) {
            return null;
        }
        List<Object> paramList = new ArrayList<>();
        for (Parameter parameter : hdl.getParameters()) {
            Class<?> type = parameter.getType();
            if (type.isAssignableFrom(e.getClass())){
                paramList.add(e);
            }else {
                paramList.add(ObjectUtils.getDefaultValue(type));
            }
        }
        try {
            return hdl.invoke(exHdl, paramList.toArray());
        } catch (IllegalAccessException | InvocationTargetException ex) {
            log.error(ex.getMessage(),ex);
            return null;
        }
    }
}
