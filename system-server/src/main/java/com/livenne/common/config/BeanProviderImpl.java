package com.livenne.common.config;

import com.livenne.BeanProvider;
import com.livenne.annotation.Component;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.Set;
import java.util.stream.Collectors;

public class BeanProviderImpl implements BeanProvider {
    @Override
    public Set<Class<?>> getClasses() {
        Reflections reflections = new Reflections("com.livenne");
        return reflections.get(Scanners.TypesAnnotated.with(Component.class).asClass())
                .stream()
                .filter(c-> !c.isAnnotation())
                .collect(Collectors.toSet());
    }
}
