package com.github.nmuzhichin.jdummy.visitor;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

/**
 * @author nmuzhichin
 * @since 24.02.2021
 */
public interface VisitorAccepter {

    <T> T accept(Parameter p);

    <T> T accept(Field f);

    <T> T accept(Class<T> c);
}
