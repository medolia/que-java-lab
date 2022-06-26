package com.medolia.demo.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * @author lbli
 * @date 2021/9/9
 */
public class GenericUtils {
    public static <T> T getSuperclassType(Object obj, int i) {
        try {
            Class<?> clazz = obj.getClass();
            Type genericSuperClass = clazz.getGenericSuperclass();
            Type[] actualArgs = ((ParameterizedType) genericSuperClass).getActualTypeArguments();
            Class<T> result = (Class<T>) actualArgs[i];
            return result.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getInterfaceType(Object obj, int i) {
        try {
            Class<?> clazz = obj.getClass();
            Type[] types = clazz.getGenericInterfaces();
            Type[] actualArgs = ((ParameterizedType) types[i]).getActualTypeArguments();
            Class<T> result = (Class<T>) actualArgs[i];
            return result.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
