package com.github.nmuzhichin.jdummy.modify;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PredefineNumberStringValueModifier implements ValueModifier {

    // todo use some file?
    private final List<String> predefineList = List.of("arg", "id", "age",
            "number", "position", "page", "int", "long");

    @SuppressWarnings("unchecked")
    @Override
    public <T> T modify(String name, Class<T> valueType) {
        return valueType == String.class && name != null
                ? (T) createValue(name)
                : null;
    }

    private String createValue(String name) {
        for (var n : predefineList) {
            if (name.toLowerCase().contains(n)) {
                return String.valueOf(ThreadLocalRandom.current().nextInt());
            }
        }
        return null;
    }
}
