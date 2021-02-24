package com.github.nmuzhichin.jdummy.visitor;

import java.util.Collection;
import java.util.Map;

final class Reflections {

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

    static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() || isPrimitiveWrapper(type);
    }

    static boolean isPrimitiveWrapper(Class<?> type) {
        return primitiveWrappers.containsKey(type);
    }

    static Class<?> primitiveUnwrap(Class<?> type) {
        return isPrimitiveWrapper(type) ? primitiveWrappers.get(type) : type;
    }

    static boolean isCollectionsOrMap(Class<?> type) {
        return Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type);
    }

    static boolean isJavaLibraryType(Class<?> type) {

        var typePackageName = type.getPackageName();
        return typePackageName.startsWith("java.lang")
                || typePackageName.startsWith("java.time")
                || typePackageName.startsWith("java.sql")
                || typePackageName.startsWith("java.math")
                || typePackageName.startsWith("sun");
    }
}
