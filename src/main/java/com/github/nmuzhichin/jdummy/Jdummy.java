package com.github.nmuzhichin.jdummy;

import com.github.nmuzhichin.jdummy.visitor.VisitorContext;

/**
 * The entry point for interacting with the library.
 * <p>
 * Current class has only static methods
 * and delegates calls to the context.
 *
 * @author nmuzhichin
 * @see com.github.nmuzhichin.jdummy.visitor.VisitorContext VisitorContext
 * @see com.github.nmuzhichin.jdummy.visitor.VisitorAccepter VisitorAccepter
 * @since 24.02.2021
 */
public abstract class Jdummy {

    Jdummy() { }

    /**
     * Delegate to the visitor accepter to instantiate
     * and populate an object of a given type.
     *
     * @param type The implementation of type
     * @param <T>  Type
     * @return Object of type T
     */
    public static <T> T of(Class<T> type) {
        return VisitorContext.currentAccepter().accept(type);
    }
}
