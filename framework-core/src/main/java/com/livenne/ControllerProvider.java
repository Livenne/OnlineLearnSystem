package com.livenne;

import java.util.Set;

public interface ControllerProvider {
    Set<Class<?>> getControllers();
}