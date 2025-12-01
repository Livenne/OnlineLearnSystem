package com.livenne;

import com.livenne.annotation.*;
import com.livenne.utils.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

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
            String apiHead = controller.getClass().getAnnotation(Controller.class).value();
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
                        boolean isMultipart = req.getContentType() != null && req.getContentType().startsWith("multipart/form-data"); //TODO adjustment needed
                        if (isMultipart) {
                            ServletInputStream inputStream = req.getInputStream();
                            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                            byte[] data = new byte[1024];
                            int nRead;
                            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                                buffer.write(data, 0, nRead);
                            }
                            arg = buffer.toByteArray();
                        }else {
                            arg = StringUtils.toObject(StringUtils.getBody(req),type);
                            System.out.println(type);
                            System.out.println(arg.getClass().getName());
                        }
                    }
                    paramList.add(arg);
                }

                try{
                    Object ret = method.invoke(controller, paramList.toArray());
                    PrintWriter out = res.getWriter();
                    out.println(StringUtils.toJson(ret));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
