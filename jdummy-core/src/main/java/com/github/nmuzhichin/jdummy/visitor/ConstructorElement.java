package com.github.nmuzhichin.jdummy.visitor;

import java.lang.reflect.Constructor;

final class ConstructorElement
        extends Element {

    private final Constructor<?> constructor;

    ConstructorElement(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConstructor(this);
    }

    @Override
    public Constructor<?> getUnderlying() {
        return constructor;
    }
}
