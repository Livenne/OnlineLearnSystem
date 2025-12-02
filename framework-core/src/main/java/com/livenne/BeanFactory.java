package com.livenne;

import com.livenne.annotation.*;
import com.livenne.utils.AnnotationUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class BeanFactory {
    public static Set<Object> beanList;
    public static Set<Class<?>> beanClassList;
    public static Set<Object> entityBeanList;
    public static Set<Class<?>> entityClassList;
    public static Set<Object> serviceBeanList;
    public static Set<Class<?>> serviceClassList;
    public static Set<Object> controllerBeanList;
    public static Set<Class<?>> controllerClassList;
    public static Set<Object> repositoryBeanList;
    public static Set<Class<?>> repositoryClassList;
    public static Set<Object> controllerAdviceBeanList;
    public static Set<Class<?>> controllerAdviceClassList;

    @SneakyThrows
    public static void initialization(Set<Class<?>> classes) {
        beanList = new HashSet<>();
        beanClassList = new HashSet<>();
        entityBeanList = new HashSet<>();
        entityClassList = new HashSet<>();
        serviceBeanList = new HashSet<>();
        serviceClassList = new HashSet<>();
        controllerBeanList = new HashSet<>();
        controllerClassList = new HashSet<>();
        controllerAdviceBeanList = new HashSet<>();
        controllerAdviceClassList = new HashSet<>();
        repositoryBeanList = new HashSet<>();
        repositoryClassList = new HashSet<>();
        for (Class<?> component : classes) {
            Object instance;
            if (component.isInterface()) {
                if (!component.isAnnotationPresent(Repository.class)) {
                    continue;
                }
                instance = ORM.sqlImplement(component);
            }else {
                instance = component.getConstructor().newInstance();
            }
            beanList.add(instance);
            beanClassList.add(component);
            if (AnnotationUtils.isAnnotationPresent(component,Service.class)){
                serviceBeanList.add(instance);
                serviceClassList.add(component);
            }
            if (AnnotationUtils.isAnnotationPresent(component, Controller.class)){
                controllerBeanList.add(instance);
                controllerClassList.add(component);
            }
            if (AnnotationUtils.isAnnotationPresent(component, ControllerAdvice.class)){
                controllerAdviceBeanList.add(instance);
                controllerAdviceClassList.add(component);
            }
            if (AnnotationUtils.isAnnotationPresent(component, Repository.class)) {
                repositoryBeanList.add(instance);
                repositoryClassList.add(component);
            }
            if (AnnotationUtils.isAnnotationPresent(component, Entity.class)) {
                entityBeanList.add(instance);
                entityClassList.add(component);
            }
        }
    }

    @SneakyThrows
    public static void dependInjection() {
        for (Object bean : beanList) {
            Class<?> component = bean.getClass();
            for (Field field : component.getDeclaredFields()) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(bean,findBean(field.getType()));
            }
        }
    }

    public static Object findBean(Class<?> beanClass) {
        for (Object bean : beanList) {
            if (bean.getClass().equals(beanClass)) {
                return bean;
            }
        }
        for (Object bean : beanList) {
            if (beanClass.isAssignableFrom(bean.getClass())) {
                return bean;
            }
        }
        return null;
    }

}
