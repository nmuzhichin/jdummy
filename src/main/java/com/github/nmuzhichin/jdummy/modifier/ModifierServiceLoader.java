package com.github.nmuzhichin.jdummy.modifier;

import java.util.ServiceLoader;
import java.util.stream.Stream;

public class ModifierServiceLoader {

    private final ServiceLoader<ValueModifier> valueModifiers;

    private ModifierServiceLoader() {
        this.valueModifiers = ServiceLoader.load(ValueModifier.class);
    }

    public static ModifierServiceLoader newServiceLoader() {
        return new ModifierServiceLoader();
    }

    public Stream<ValueModifier> openValueModifierStream() {
        return valueModifiers.stream().map(ServiceLoader.Provider::get);
    }
}
