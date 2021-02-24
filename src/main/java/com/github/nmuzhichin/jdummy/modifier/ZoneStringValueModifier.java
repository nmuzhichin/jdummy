package com.github.nmuzhichin.jdummy.modifier;

import java.util.concurrent.ThreadLocalRandom;

public class ZoneStringValueModifier implements ValueModifier {

    private static final String zoneName = "zone";

    @SuppressWarnings("unchecked")
    @Override
    public <T> T modify(String name, Class<T> valueType) {

        return valueType == String.class && name != null && name.toLowerCase().contains(zoneName)
                ? (T) String.valueOf(ThreadLocalRandom.current().nextInt(-12, 15))
                : null;
    }
}
