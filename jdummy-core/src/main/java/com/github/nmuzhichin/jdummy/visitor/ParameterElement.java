package com.github.nmuzhichin.jdummy.visitor;

import java.lang.reflect.Parameter;

final class ParameterElement
        extends Element {

    private final Parameter parameter;

    ParameterElement(Parameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitParameter(this);
    }

    @Override
    public Parameter getUnderlying() {
        return parameter;
    }
}
