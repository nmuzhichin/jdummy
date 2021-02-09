package com.github.nmuzhichin.jdummy.visitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

/**
 * @author nmuzhichin
 * @since 08.02.2021
 */
public interface Visitor {

    default void visitType(Class<?> type) {
    }

    default void visitConstructor(Constructor<?> constructor) {
    }

    default void visitParameter(Parameter parameter) {
    }

    default void visitField(Field field) {
    }
}
