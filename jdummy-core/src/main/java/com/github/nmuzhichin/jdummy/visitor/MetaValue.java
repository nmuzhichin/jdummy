package com.github.nmuzhichin.jdummy.visitor;

import java.lang.reflect.Type;

final class MetaValue {

    private final String meta;

    private final Type reflectType;

    private Object value;

    MetaValue(String meta, Type reflectType) {
        this.meta = meta;
        this.reflectType = reflectType;
    }

    String getMeta() {
        return meta;
    }

    Type getReflectType() {
        return reflectType;
    }

    Object getValue() {
        return value;
    }

    void setValue(Object value) {
        this.value = value;
    }

    boolean isEmpty() {
        return value == null;
    }
}
