package com.github.nmuzhichin.jdummy.modify;

/**
 * @author nmuzhichin
 * @since 20.02.2021
 */
public interface ValueModifier {

    <T> T modify(String name, Class<T> valueType);
}
