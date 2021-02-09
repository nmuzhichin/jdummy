package com.github.nmuzhichin.jdummy.element;

import com.github.nmuzhichin.jdummy.visitor.Visitor;

final class TypeElement<T> extends Element {

    private final Class<T> cls;

    TypeElement(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitType(cls);
    }
}
