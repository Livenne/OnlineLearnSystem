package com.livenne.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {
    public static Set<Class<?>> getClasses(String packageName) {
        Set<Class<?>> classes = new HashSet<>();

        //...

        return classes;
    }

    public static Class<?> getSubType(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            return (Class<?>) types[0];
        }
        return null;
    }

}
