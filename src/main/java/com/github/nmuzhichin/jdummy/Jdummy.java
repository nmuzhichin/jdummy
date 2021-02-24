package com.github.nmuzhichin.jdummy;

import com.github.nmuzhichin.jdummy.visitor.VisitorContext;

import java.util.stream.Stream;

public abstract class Jdummy {

    Jdummy() {
        // reservation
    }

    public static <T> T of(Class<T> type) {
        return VisitorContext.currentAccepter().accept(type);
    }

    public static <T> Stream<T> manyOf(Class<T> type) {
        return Stream.generate(() -> of(type));
    }
}
