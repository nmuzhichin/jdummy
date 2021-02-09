package com.github.nmuzhichin.jdummy.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

class ReflectionsTest {

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
        Assertions.assertTrue(Reflections.isPrimitiveOrWrapper(type));
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
        Assertions.assertTrue(Reflections.isCollectionsOrMap(type));
    }

    @Test
    void isPrimitiveOrWrapper() {
        Assertions.assertFalse(Reflections.isPrimitiveOrWrapper(List.class));
    }

    @Test
    void isCollectionsOrMap() {
        Assertions.assertFalse(Reflections.isCollectionsOrMap(Void.class));
    }
}