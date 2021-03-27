package com.github.nmuzhichin.jdummy.visitor;

/**
 * @author nmuzhichin
 * @since 09.02.2021
 */
abstract class Element {

    public abstract void accept(Visitor visitor);

    public abstract Object getUnderlying();
}
