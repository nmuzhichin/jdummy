package com.github.nmuzhichin.jdummy;

import com.github.nmuzhichin.jdummy.visitor.JdummyContext;

/**
 * The entry point for interacting with the library.
 * <p>
 * Current class has only static methods
 * and delegates calls to the context.
 *
 * @author nmuzhichin
 * @see com.github.nmuzhichin.jdummy.visitor.JdummyContext JdummyContext
 * @see com.github.nmuzhichin.jdummy.ElementAccepter ElementAccepter
 * @since 24.02.2021
 */
public abstract class Jdummy {

    Jdummy() { }

    /**
     * Delegate to the element accepter to instantiate
     * and populate an object of a given type.
     *
     * @param type The implementation of type
     * @param <T>  Type
     * @return Object of type T
     */
    public static <T> T of(Class<T> type) {
        return JdummyContext.currentAccepter().accept(type);
    }

    /**
     * Write the object to the context cache
     * for use during instantiate.
     * <p>
     * Null is ignored
     *
     * @param value Object or null
     */
    public static void putCache(Object value) {
        if (value != null) {
            JdummyContext.currentCacheWriter().write(value.getClass(), value);
        }
    }
}
