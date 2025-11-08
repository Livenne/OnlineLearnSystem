package com.livenne;


import com.livenne.annotation.Controller;
import com.livenne.annotation.GetMapping;
import com.livenne.annotation.PostMapping;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ServiceLoader;
import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

@WebListener
public class DynamicServletRegistrar implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ServiceLoader<ControllerProvider> loader = ServiceLoader.load(ControllerProvider.class);
        for (ControllerProvider controllerProvider : loader) {
            Set<Class<?>> controllers = controllerProvider.getControllers();
            try{
                for (Class<?> controller : controllers) {
                    if (controller.getAnnotation(Controller.class) == null) continue;
                    String baseUrl = controller.getAnnotation(Controller.class).value();
                    for (Method method : controller.getDeclaredMethods()) {
                        method.setAccessible(true);
                        String className = String.format("%s.Servlet_%s",controller.getName(),method.getName());
                        String option;
                        String subPath;
                        Object interceptor = new ServletInterceptor(method,controller.getConstructor().newInstance());
                        if (method.isAnnotationPresent(GetMapping.class)){
                            option = "doGet";
                            subPath = method.getAnnotation(GetMapping.class).value();
                        } else if (method.isAnnotationPresent(PostMapping.class)) {
                            option = "doPost";
                            subPath = method.getAnnotation(PostMapping.class).value();
                        } else continue;
                        Class<? extends HttpServlet> servletClass = new ByteBuddy()
                                .subclass(HttpServlet.class)
                                .name(className)
                                .method(ElementMatchers
                                        .named(option)
                                        .and(takesArguments(HttpServletRequest.class, HttpServletResponse.class)))
                                .intercept(MethodDelegation.to(interceptor))
                                .make()
                                .load(getClass().getClassLoader())
                                .getLoaded();
                        ServletRegistration.Dynamic registration =
                                context.addServlet(className, servletClass.getDeclaredConstructor().newInstance());
                        registration.addMapping(baseUrl + subPath);
                        registration.setLoadOnStartup(1);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
