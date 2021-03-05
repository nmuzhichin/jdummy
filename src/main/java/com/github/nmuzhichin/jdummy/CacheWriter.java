package com.github.nmuzhichin.jdummy;

/**
 * Operations for writing a value and checking a value by type in the cache
 *
 * @author nmuzhichin
 * @since 24.02.2021
 */
public interface CacheWriter {

    /**
     * Write a value to the cache according to the specified type
     *
     * @param type  Value type
     * @param value Any object
     */
    void write(Class<?> type, Object value);


    /**
     * Check that the cache contains a value of the given type
     *
     * @param type Value type
     * @return true, if the cache contains a value
     */
    boolean contains(Class<?> type);
}
