package com.github.nmuzhichin.jdummy.visitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

enum OverflowGuard {
    CACHE;

    private final Map<Class<?>, Object> stopList = new ConcurrentHashMap<>(32);

    boolean isPresent(Class<?> clz) {
        return stopList.containsKey(clz);
    }

    void remove(Class<?> clz) {
        stopList.remove(clz);
    }

    void add(Class<?> clz, Object value) {
        stopList.put(clz, value);
    }

    public Object get(Class<?> type) {
        return stopList.get(type);
    }
}
