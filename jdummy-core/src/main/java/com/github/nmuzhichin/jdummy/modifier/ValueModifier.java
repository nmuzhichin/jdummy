package com.github.nmuzhichin.jdummy.modifier;

/**
 * @author nmuzhichin
 * @since 20.02.2021
 */
public abstract class ValueModifier<T> {

    public abstract Class<T> valueType();

    public final T modify(String meta) {
        return meta != null && verify(meta) ? createValue(meta) : null;
    }

    public abstract T createValue(String meta);

    public boolean verify(String meta) {
        return true;
    }
}
