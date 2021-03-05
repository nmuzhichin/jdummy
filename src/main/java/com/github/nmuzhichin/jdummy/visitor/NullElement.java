package com.github.nmuzhichin.jdummy.visitor;

class NullElement
        extends Element {

    static final Element NULL_ELEMENT = new NullElement();

    private NullElement() {
        // only instance
    }

    @Override
    public void accept(Visitor visitor) { }

    @Override
    public Void getUnderlying() {
        return null;
    }
}
