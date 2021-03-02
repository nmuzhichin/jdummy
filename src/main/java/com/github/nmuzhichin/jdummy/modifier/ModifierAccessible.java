package com.github.nmuzhichin.jdummy.modifier;

import java.util.Optional;

/**
 * @author nmuzhichin
 * @since 02.03.2021
 */
public interface ModifierAccessible {

    <T> Optional<T> modifyByType(Class<T> type, String meta);
}
