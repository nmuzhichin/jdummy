package com.github.nmuzhichin.jdummy.modifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public final class ModifierServiceLoader {

    private final Map<Class<?>, List<ValueModifier>> modifiers;

    public ModifierServiceLoader() {
        this.modifiers = load();
    }

    private Map<Class<?>, List<ValueModifier>> load() {
        return ServiceLoader.load(ValueModifier.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingByConcurrent(ValueModifier::valueType));
    }

    public Map<Class<?>, List<ValueModifier>> getModifiers() {
        return modifiers;
    }
}
