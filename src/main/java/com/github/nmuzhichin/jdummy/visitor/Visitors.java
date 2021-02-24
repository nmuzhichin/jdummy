package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.element.Elements;
import com.github.nmuzhichin.jdummy.misc.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public final class Visitors {

    private Visitors() {
        // use static methods
    }

    private static Visitor findVisitor(Class<?> clazz, MetaValue type) {
        Visitor visitor;
        if (Reflections.isPrimitiveOrWrapper(clazz)) {
            visitor = new PrimitiveVisitor(type);
        } else if (clazz.isEnum()) {
            visitor = new EnumVisitor(type);
        } else if (clazz.isArray()) {
            visitor = new ArrayVisitor(type);
        } else if (Reflections.isCollectionsOrMap(clazz)) {
            visitor = new CollectionVisitor(type);
        } else if (Reflections.isJavaLibraryType(clazz)) {
            visitor = new JavaCoreVisitor(type);
        } else {
            visitor = new PojoVisitor(type);
        }
        return visitor;
    }

    @SuppressWarnings("unchecked")
    private static <T> T accept(MetaValue metaValue, Class<?> type) {

        Object tVal;
        if (OverflowGuard.CACHE.isPresent(type)) {
            tVal = OverflowGuard.CACHE.get(type);
        } else {
            var visitor = findVisitor(type, metaValue);
            var elements = Elements.newAllElements(type);
            for (var e : elements) {
                e.accept(visitor);
            }
            tVal = metaValue.getValue();
        }

        return (T) tVal;
    }

    public static <T> T accept(Parameter p) {
        var metaValue = new MetaValue(p.getName(), p.getParameterizedType());
        return accept(metaValue, p.getType());
    }

    public static <T> T accept(Field f) {
        var metaValue = new MetaValue(f.getName(), f.getGenericType());
        return accept(metaValue, f.getType());
    }

    public static <T> T accept(Class<T> c) {
        var metaValue = new MetaValue(c.getSimpleName(), null);
        return accept(metaValue, c);
    }
}
