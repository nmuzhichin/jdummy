package com.github.nmuzhichin.jdummy.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ReflectionsTest {

    private static Stream<Arguments> unwrapTestFactory() {
        return Stream.of(
                arguments(Void.class, void.class),
                arguments(Boolean.class, boolean.class),
                arguments(Byte.class, byte.class),
                arguments(Short.class, short.class),
                arguments(Character.class, char.class),
                arguments(Integer.class, int.class),
                arguments(Long.class, long.class),
                arguments(Float.class, float.class),
                arguments(Double.class, double.class)
        );
    }

    @ParameterizedTest
    @ValueSource(classes = {
            Void.class, Boolean.class, Byte.class,
            Short.class, Character.class, Integer.class,
            Long.class, Float.class, Double.class,
            void.class, boolean.class, byte.class,
            short.class, char.class, int.class,
            long.class, float.class, double.class
    })
    void isPrimitiveOrWrapper(Class<?> type) {
        assertTrue(Reflections.isPrimitiveOrWrapper(type));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            HashMap.class, TreeMap.class, Hashtable.class, WeakHashMap.class,
            EnumMap.class, SortedMap.class, AbstractMap.class, Map.class,
            LinkedHashMap.class, Queue.class, AbstractCollection.class,
            Collection.class, List.class, Set.class, SortedSet.class,
            ArrayList.class, LinkedList.class, Vector.class, Deque.class,
            AbstractList.class, AbstractSet.class, HashSet.class, TreeSet.class,
            EnumSet.class, Stack.class, ArrayDeque.class, PriorityQueue.class
    })
    void isCollectionsOrMap(Class<?> type) {
        assertTrue(Reflections.isCollectionsOrMap(type));
    }

    @ParameterizedTest
    @MethodSource("unwrapTestFactory")
    void primitiveUnwrap(Class<?> actual, Class<?> expected) {
        assertEquals(expected, Reflections.primitiveUnwrap(actual));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            Void.class, Boolean.class, Byte.class,
            Short.class, Character.class, Integer.class,
            Long.class, Float.class, Double.class
    })
    void isPrimitiveWrapper(Class<?> type) {
        assertTrue(Reflections.isPrimitiveWrapper(type));
    }

    @ParameterizedTest
    @ValueSource(classes = {
            Object.class, String.class,
            Instant.class, LocalDateTime.class,
            BigInteger.class, BigDecimal.class,
            sun.misc.Unsafe.class
    })
    void isJavaLibraryType(Class<?> type) {
        assertTrue(Reflections.isJavaLibraryType(type));
    }

    @Test
    void isPrimitiveOrWrapper() {
        assertFalse(Reflections.isPrimitiveOrWrapper(List.class));
    }

    @Test
    void isCollectionsOrMap() {
        assertFalse(Reflections.isCollectionsOrMap(Void.class));
    }

    @Test
    void isPrimitiveWrapper() {
        assertFalse(Reflections.isPrimitiveWrapper(String.class));
    }

    @Test
    void primitiveUnwrap() {
        assertEquals(List.class, Reflections.primitiveUnwrap(List.class));
    }

    @Test
    void isJavaLibraryType() {
        assertFalse(Reflections.isJavaLibraryType(List.class));
    }
}