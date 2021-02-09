package com.github.nmuzhichin.jdummy.visitor;

abstract class AbstractMetaValueVisitor implements Visitor {

    protected final ValueHolder valueHolder;

    protected final String meta;

    protected final java.lang.reflect.Type typeMeta;

    AbstractMetaValueVisitor(MetaValueType type) {
        this.valueHolder = type.getValueHolder();
        this.meta = type.getMeta();
        this.typeMeta = type.getReflectType();
    }
}
