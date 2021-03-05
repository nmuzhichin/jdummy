package com.github.nmuzhichin.jdummy.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings({"unchecked", "rawtypes"})
final class CollectionVisitor extends AbstractMetaValueVisitor {

    private static final Logger log = LoggerFactory.getLogger(CollectionVisitor.class);

    private static final Integer COLLECTION_SIZE_FOR_DUMMY = Integer.getInteger("collection.dummy.size", 4);

    private static final Map<Class<?>, Supplier<Map>> MAP_SUPPLIER = Map.of(
            HashMap.class, HashMap::new,
            LinkedHashMap.class, LinkedHashMap::new,
            IdentityHashMap.class, IdentityHashMap::new,
            WeakHashMap.class, WeakHashMap::new,
            Hashtable.class, Hashtable::new,
            TreeMap.class, TreeMap::new
    );

    private static final Map<Class<?>, Supplier<Set>> SET_SUPPLIER = Map.of(
            HashSet.class, HashSet::new,
            LinkedHashSet.class, LinkedHashSet::new,
            TreeSet.class, TreeSet::new
    );

    private static final Map<Class<?>, Supplier<List>> LIST_SUPPLIER = Map.of(
            ArrayList.class, ArrayList::new,
            LinkedList.class, LinkedList::new,
            Vector.class, Vector::new,
            Stack.class, Stack::new
    );

    private static final Map<Class<?>, Supplier<Queue>> QUEUE_SUPPLIER = Map.of(
            PriorityQueue.class, PriorityQueue::new,
            ArrayDeque.class, ArrayDeque::new,
            LinkedList.class, LinkedList::new
    );

    CollectionVisitor(MetaValue type) {
        super(type);
    }

    @Override
    public void visitType(Class<?> type) {

        if (Map.class.isAssignableFrom(type)) {
            visitAsMap((Class<? extends Map>) type);
        } else if (Set.class.isAssignableFrom(type)) {
            visitAsSet(((Class<? extends Set>) type));
        } else if (List.class.isAssignableFrom(type)) {
            visitAsList(((Class<? extends List>) type));
        } else if (Deque.class.isAssignableFrom(type)) {
            visitAsDeque(((Class<? extends Deque>) type));
        } else if (Queue.class.isAssignableFrom(type)) {
            visitAsQueue(((Class<? extends Queue>) type));
        }
    }

    @SuppressWarnings("RedundantCast")
    private void visitAsMap(Class<? extends Map> mapType) {

        var isEnumMap = EnumMap.class.isAssignableFrom(mapType);
        var map = isEnumMap ? null : MAP_SUPPLIER.getOrDefault(mapType, HashMap::new).get();
        var types = getGenericType();
        if (types.length > 1) {
            var keyType = (Class<?>) types[0];
            var valueType = (Class<?>) types[1];
            if (isEnumMap) {
                map = new EnumMap<>((Class<? extends Enum>) keyType);
            }
            var keyDummies = dummies(keyType);
            var valueDummies = dummies(valueType);
            for (int i = 0; i < COLLECTION_SIZE_FOR_DUMMY; i++) {
                map.put(keyDummies.get(i), valueDummies.get(i));
            }
        }
        metaValue.setValue(map);
    }

    private void visitAsSet(Class<? extends Set> setType) {

        var isEnumSet = EnumSet.class.isAssignableFrom(setType);
        var set = isEnumSet ? null : SET_SUPPLIER.getOrDefault(setType, HashSet::new).get();
        var types = getGenericType();
        if (types.length > 0) {
            var clz = ((Class) types[0]);
            if (isEnumSet) {
                metaValue.setValue(EnumSet.allOf(clz));
                return;
            } else if (set != null) {
                set.addAll(dummies(clz));
            }
        }

        metaValue.setValue(set);
    }

    private void visitAsList(Class<? extends List> listType) {

        setWithDummies(LIST_SUPPLIER.getOrDefault(listType, ArrayList::new).get());
    }

    private void visitAsDeque(Class<? extends Deque> dequeueType) {

        setWithDummies(QUEUE_SUPPLIER.getOrDefault(dequeueType, ArrayDeque::new).get());
    }

    private void visitAsQueue(Class<? extends Queue> queueType) {

        setWithDummies(QUEUE_SUPPLIER.getOrDefault(queueType, PriorityQueue::new).get());
    }

    private Type[] getGenericType() {

        Type typeMeta = metaValue.getReflectType();
        if (typeMeta instanceof ParameterizedType) {
            var arguments = ((ParameterizedType) typeMeta).getActualTypeArguments();
            if (arguments.length > 0) {
                return arguments;
            }
        }
        return new Type[0];
    }

    private void setWithDummies(Collection collection) {

        var types = getGenericType();
        if (types.length > 0) {
            var clz = (Class) types[0];
            collection.addAll(dummies(clz));
        }
        metaValue.setValue(collection);
    }

    private List<Object> dummies(Class<?> clz) {

        try {
            return Stream.generate(() -> visitorAccepter.accept(clz))
                    .limit(COLLECTION_SIZE_FOR_DUMMY)
                    .collect(Collectors.toList());
        } catch (StackOverflowError overflowError) {
            if (log.isErrorEnabled()) {
                log.error(overflowError.getMessage(), overflowError);
            }
            return List.of();
        }
    }
}
