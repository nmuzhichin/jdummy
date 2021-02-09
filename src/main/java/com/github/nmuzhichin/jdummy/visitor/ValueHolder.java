package com.github.nmuzhichin.jdummy.visitor;

class ValueHolder {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == null;
    }
}
