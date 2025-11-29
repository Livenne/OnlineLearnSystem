package com.livenne;

import java.util.Set;

public interface BeanProvider {
    Set<Class<?>> getClasses();
}