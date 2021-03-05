package com.github.nmuzhichin.jdummy;

/**
 * @author nmuzhichin
 * @since 24.02.2021
 */
public interface CacheWriter {

    void write(Class<?> type, Object value);

    boolean contains(Class<?> type);
}
