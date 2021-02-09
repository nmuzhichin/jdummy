package com.github.nmuzhichin.jdummy.visitor;

import java.util.HashSet;
import java.util.Set;

enum OverflowGuard {
    INSTANCE;

    private final Set<Class<?>> stopList = new HashSet<>();

    boolean underProtect(Class<?> clz) {
        return stopList.contains(clz);
    }

    void unprotect(Class<?> clz) {
        stopList.remove(clz);
    }

    void protect(Class<?> clz) {
        stopList.add(clz);
    }
}
