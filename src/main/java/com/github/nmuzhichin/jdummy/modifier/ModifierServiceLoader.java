package com.github.nmuzhichin.jdummy.modifier;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class ModifierServiceLoader {

    public static Map<Class<?>, List<ValueModifier>> load() {
        return ServiceLoader.load(ValueModifier.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.groupingByConcurrent(ValueModifier::valueType));
    }
}
