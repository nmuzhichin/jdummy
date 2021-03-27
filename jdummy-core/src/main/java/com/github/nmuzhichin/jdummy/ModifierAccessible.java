package com.github.nmuzhichin.jdummy;

import java.util.Optional;

/**
 * Access to modifiers
 *
 * @author nmuzhichin
 * @see com.github.nmuzhichin.jdummy.modifier.ValueModifier ValueModifier
 * @since 02.03.2021
 */
public interface ModifierAccessible {

    /**
     * Modify value using ValueModifiers
     *
     * @param type Class
     * @param meta Element info (field name, for example)
     * @param <T>  Type
     * @return Modified value of type T or empty
     */
    <T> Optional<T> modifyByType(Class<T> type, String meta);
}
