package com.github.nmuzhichin.jdummy.element;

import com.github.nmuzhichin.jdummy.visitor.Visitor;

/**
 * @author nmuzhichin
 * @since 09.02.2021
 */
public abstract class Element {

    public abstract void accept(Visitor visitor);
}
