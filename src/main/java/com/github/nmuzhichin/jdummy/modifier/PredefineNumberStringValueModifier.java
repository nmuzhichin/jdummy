package com.github.nmuzhichin.jdummy.modifier;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PredefineNumberStringValueModifier extends ValueModifier {

    // todo use some file?
    private final Set<String> predefineList = Set.of("arg", "id", "age",
            "number", "position", "page", "int", "long");

    @SuppressWarnings("unchecked")
    @Override
    public <T> T createValue(String name) {
        return (T) String.valueOf(ThreadLocalRandom.current().nextInt());
    }

    @Override
    public boolean verify(String meta) {
        var lowerMeta = meta.toLowerCase();
        for (var n : predefineList) {
            if (lowerMeta.contains(n)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Class<?> valueType() {
        return String.class;
    }
}
