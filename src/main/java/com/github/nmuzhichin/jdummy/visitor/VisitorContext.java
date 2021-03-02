package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.cache.CacheReadWriter;
import com.github.nmuzhichin.jdummy.cache.CacheWriter;
import com.github.nmuzhichin.jdummy.element.Elements;
import com.github.nmuzhichin.jdummy.modifier.ModifierAccessible;
import com.github.nmuzhichin.jdummy.modifier.ModifierServiceLoader;
import com.github.nmuzhichin.jdummy.modifier.ValueModifier;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class VisitorContext implements VisitorAccepter,
        CacheReadWriter, ModifierAccessible {

    private static final ThreadLocal<VisitorContext> context =
            ThreadLocal.withInitial(VisitorContext::new);

    private static final Map<Class<?>, List<ValueModifier>> modifiers;

    static {
        modifiers = ModifierServiceLoader.load();
    }

    private final Map<Class<?>, Object> cacheReadinessValue;

    private VisitorContext() {
        this.cacheReadinessValue = new ConcurrentHashMap<>(32);
    }

    public static VisitorAccepter currentAccepter() {
        return context.get();
    }

    public static CacheWriter currentCacheWriter() {
        return context.get();
    }

    public static ModifierAccessible currentValueModifiers() {
        return context.get();
    }

    @Override
    public <T> T accept(Parameter p) {
        var metaValue = new MetaValue(p.getName(), p.getParameterizedType());
        return accept(metaValue, p.getType());
    }

    @Override
    public <T> T accept(Field f) {
        var metaValue = new MetaValue(f.getName(), f.getGenericType());
        return accept(metaValue, f.getType());
    }

    @Override
    public <T> T accept(Class<T> c) {
        var metaValue = new MetaValue(c.getSimpleName(), null);
        return accept(metaValue, c);
    }

    @SuppressWarnings("unchecked")
    private <T> T accept(MetaValue metaValue, Class<?> type) {

        Object tVal;
        if (cacheReadinessValue.containsKey(type)) {
            tVal = cacheReadinessValue.get(type);
        } else {
            var visitor = Visitors.findVisitor(type, metaValue);
            var elements = Elements.newAllElements(type);
            for (var e : elements) {
                e.accept(visitor);
            }
            tVal = metaValue.getValue();
        }
        return (T) tVal;
    }

    @Override
    public Object read(Class<?> type) {
        return cacheReadinessValue.get(type);
    }

    @Override
    public void write(Class<?> type, Object value) {
        cacheReadinessValue.put(type, value);
    }

    @Override
    public boolean contains(Class<?> type) {
        return cacheReadinessValue.containsKey(type);
    }

    @Override
    public <T> Optional<T> modifyByType(Class<T> type, String meta) {
        return modifiers.getOrDefault(type, List.of())
                .stream()
                .map(valueModifier -> (T) valueModifier.modify(meta))
                .filter(Objects::nonNull)
                .findFirst();
    }
}
