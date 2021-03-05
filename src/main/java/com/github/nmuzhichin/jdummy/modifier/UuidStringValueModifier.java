package com.github.nmuzhichin.jdummy.modifier;

import java.util.UUID;

public class UuidStringValueModifier extends ValueModifier<String> {

    private static final String uuidName = "uuid";

    @Override
    public Class<String> valueType() {
        return String.class;
    }

    @Override
    public String createValue(String meta) {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean verify(String meta) {
        return meta.toLowerCase().contains(uuidName);
    }
}
