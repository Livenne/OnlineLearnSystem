package com.livenne.common.config;

import com.livenne.ControllerProvider;
import com.livenne.annotation.Controller;
import org.reflections.Reflections;

import java.util.Set;

public class SystemServerControllerProvider implements ControllerProvider {
    @Override
    public Set<Class<?>> getControllers() {
        Reflections reflections = new Reflections("com.livenne");
        return reflections.getTypesAnnotatedWith(Controller.class);
    }
}