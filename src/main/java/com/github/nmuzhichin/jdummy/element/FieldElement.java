package com.github.nmuzhichin.jdummy.element;

import com.github.nmuzhichin.jdummy.visitor.Visitor;

import java.lang.reflect.Field;

final class FieldElement extends Element {

    private final Field field;

    FieldElement(Field field) {
        this.field = field;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitField(field);
    }
}
