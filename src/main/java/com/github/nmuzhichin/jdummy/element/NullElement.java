package com.github.nmuzhichin.jdummy.element;

import com.github.nmuzhichin.jdummy.visitor.Visitor;

public class NullElement extends Element {

    public static final Element NULL_ELEMENT = new NullElement();

    private NullElement() {
        // only instance
    }

    @Override
    public void accept(Visitor visitor) {
    }
}
