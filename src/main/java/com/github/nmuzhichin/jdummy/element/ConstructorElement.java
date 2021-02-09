package com.github.nmuzhichin.jdummy.element;

import com.github.nmuzhichin.jdummy.visitor.Visitor;

import java.lang.reflect.Constructor;

final class ConstructorElement extends Element {

    private final Constructor<?> constructor;

    ConstructorElement(Constructor<?> constructor) {
        this.constructor = constructor;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConstructor(constructor);
    }

    Constructor<?> getConstructor() {
        return constructor;
    }
}
