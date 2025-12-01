package com.livenne;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

@WebListener
public class Boot implements ServletContextListener {
    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceLoader<BeanProvider> loader = ServiceLoader.load(BeanProvider.class);
        Set<Class<?>> classes = new HashSet<>();
        loader.forEach(provider -> classes.addAll(provider.getClasses()));
        BeanFactory.initialization(classes);
        BeanFactory.dependInjection();
        ORM.initialization();
        ORM.automationDDL(BeanFactory.entityClassList);
        RouterFilter.register(sce.getServletContext());
    }
}
