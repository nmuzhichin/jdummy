package com.github.nmuzhichin.jdummy.misc;

import java.util.Collection;
import java.util.Map;

public final class Reflections {

    private static final Map<Class<?>, Class<?>> primitiveWrappers;

    static {
        primitiveWrappers = Map.of(
                Void.class, void.class,
                Boolean.class, boolean.class,
                Byte.class, byte.class,
                Short.class, short.class,
                Character.class, char.class,
                Integer.class, int.class,
                Long.class, long.class,
                Float.class, float.class,
                Double.class, double.class
        );
    }

    private Reflections() {
        // use static methods
    }

    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() || isPrimitiveWrapper(type);
    }

    public static boolean isPrimitiveWrapper(Class<?> type) {
        return primitiveWrappers.containsKey(type);
    }

    public static Class<?> primitiveUnwrap(Class<?> type) {
        return isPrimitiveWrapper(type) ? primitiveWrappers.get(type) : type;
    }

    public static boolean isCollectionsOrMap(Class<?> type) {
        return Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type);
    }

    public static boolean isJavaLibraryType(Class<?> type) {

        var typePackageName = type.getPackageName();
        return typePackageName.startsWith("java.lang")
                || typePackageName.startsWith("java.time")
                || typePackageName.startsWith("java.sql")
                || typePackageName.startsWith("java.math")
                || typePackageName.startsWith("sun");
    }
}
