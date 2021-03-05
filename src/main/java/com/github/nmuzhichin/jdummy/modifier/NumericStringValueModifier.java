package com.github.nmuzhichin.jdummy.modifier;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class NumericStringValueModifier extends ValueModifier<String> {

    // todo use some file?
    private final Set<String> predefineList = Set.of("arg", "age",
            "number", "position", "page", "int", "long");

    @Override
    public String createValue(String name) {
        return String.valueOf(ThreadLocalRandom.current().nextInt());
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
    public Class<String> valueType() {
        return String.class;
    }
}
