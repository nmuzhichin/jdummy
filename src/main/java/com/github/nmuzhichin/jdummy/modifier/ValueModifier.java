package com.github.nmuzhichin.jdummy.modifier;

/**
 * @author nmuzhichin
 * @since 20.02.2021
 */
public abstract class ValueModifier {

    public abstract Class<?> valueType();

    public final <T> T modify(String meta) {
        return meta != null && verify(meta) ? createValue(meta) : null;
    }

    abstract <T> T createValue(String meta);

    abstract boolean verify(String meta);
}
