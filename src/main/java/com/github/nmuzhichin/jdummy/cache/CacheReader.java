package com.github.nmuzhichin.jdummy.cache;

/**
 * @author nmuzhichin
 * @since 24.02.2021
 */
public interface CacheReader {

    Object read(Class<?> type);
}
