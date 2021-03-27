package com.github.nmuzhichin.jdummy.visitor;

import java.lang.reflect.Field;

final class FieldElement
        extends Element {

    private final Field field;

    FieldElement(Field field) {
        this.field = field;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitField(this);
    }

    @Override
    public Field getUnderlying() {
        return field;
    }
}
