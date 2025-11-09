package com.livenne;

import com.livenne.annotation.Autowired;
import com.livenne.annotation.RequestBody;
import com.livenne.annotation.RequestParm;
import com.livenne.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.implementation.bind.annotation.*;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ServletInterceptor {
    private final Method method;
    private final Object instance;
    public ServletInterceptor(Method method,Object instance) {
        this.method = method;
        this.instance = instance;
    }
    @RuntimeType
    public void intercept(@Argument(0) HttpServletRequest request,
                          @Argument(1) HttpServletResponse response) throws Exception {;

        for (Field field : instance.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Autowired.class)) {
                if (field.getType().equals(HttpServletRequest.class)) {
                    field.set(instance, request);
                }else if (field.getType().equals(HttpServletResponse.class)) {
                    field.set(instance, response);
                }
            }
        }

        List<Object> argList = new ArrayList<>();
        for (Parameter parameter : method.getParameters()) {
            Object obj = null;
            Class<?> parameterType = parameter.getType();
            if (parameter.isAnnotationPresent(RequestBody.class)){
                obj = StringUtils.formJson(StringUtils.getBody(request),parameterType);
            }else if (parameter.isAnnotationPresent(RequestParm.class)){
                obj = StringUtils.formJson(request.getParameter(parameter.getAnnotation(RequestParm.class).value()),parameterType);
            }
            argList.add(obj);
        }
        Object ret = method.invoke(instance, argList.toArray());
        PrintWriter out = response.getWriter();
        if (ret == null) return;
        String result = StringUtils.toJson(ret);
        if (result == null) return;
        out.write(result);
    }
}
