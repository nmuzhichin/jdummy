package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.misc.Reflections;

import java.lang.reflect.Type;

public class MetaValueType {

    private final Class<?> clazz;

    private final String meta;

    private final Type reflectType;

    private final ValueHolder valueHolder;

    private final Visitor visitor;

    public MetaValueType(Class<?> clazz, String meta, Type reflectType) {
        this.clazz = clazz;
        this.reflectType = reflectType;
        this.meta = meta;
        this.valueHolder = new ValueHolder();
        this.visitor = byClass(clazz);
    }

    private Visitor byClass(Class<?> clazz) {
        Visitor visitor;
        if (Reflections.isPrimitiveOrWrapper(clazz)) {
            visitor = new PrimitiveVisitor(this);
        } else if (clazz.isEnum()) {
            visitor = new EnumVisitor(this);
        } else if (clazz.isArray()) {
            visitor = new ArrayVisitor(this);
        } else if (Reflections.isCollectionsOrMap(clazz)) {
            visitor = new CollectionVisitor(this);
        } else if (Reflections.isJavaLibraryType(clazz)) {
            visitor = new JavaCoreVisitor(this);
        } else {
            visitor = new PojoVisitor(this);
        }
        return visitor;
    }

    Class<?> getClassType() {
        return clazz;
    }

    String getMeta() {
        return meta;
    }

    ValueHolder getValueHolder() {
        return valueHolder;
    }

    Visitor getVisitor() {
        return visitor;
    }

    Type getReflectType() {
        return reflectType;
    }
}
