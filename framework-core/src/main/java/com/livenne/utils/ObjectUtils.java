package com.livenne.utils;

public class ObjectUtils {
    public static Object getDefaultValue(Class<?> type){
        if (type.equals(String.class)) return null;
        if (type.equals(boolean.class)) return false;
        if (type.equals(Boolean.class)) return false;
        if (type.equals(int.class)) return 0;
        if (type.equals(Integer.class)) return 0;
        if (type.equals(double.class)) return 0;
        if (type.equals(Double.class)) return 0;
        if (type.equals(float.class)) return 0;
        if (type.equals(Float.class)) return 0;
        if (type.equals(long.class)) return 0;
        if (type.equals(Long.class)) return 0;
        if (type.equals(byte.class)) return 0;
        if (type.equals(Byte.class)) return 0;
        if (type.equals(short.class)) return 0;
        if (type.equals(Short.class)) return 0;
        if (type.equals(char.class)) return 0;
        if (type.equals(Character.class)) return 0;
        return null;
    }
}
