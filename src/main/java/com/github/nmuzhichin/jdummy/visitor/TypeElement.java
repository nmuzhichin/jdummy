package com.github.nmuzhichin.jdummy.visitor;

final class TypeElement
        extends Element {

    private final Class<?> cls;

    TypeElement(Class<?> cls) {
        this.cls = cls;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitType(this);
    }

    @Override
    public Class<?> getUnderlying() {
        return cls;
    }
}
