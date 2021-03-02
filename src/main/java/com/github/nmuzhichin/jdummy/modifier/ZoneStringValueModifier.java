package com.github.nmuzhichin.jdummy.modifier;

import java.util.concurrent.ThreadLocalRandom;

public class ZoneStringValueModifier extends ValueModifier {

    private static final String zoneName = "zone";

    @SuppressWarnings("unchecked")
    @Override
    public <T> T createValue(String meta) {
        int id = ThreadLocalRandom.current().nextInt(-12, 15);
        return (T) (id >= 0 ? "+" + id : String.valueOf(id));
    }

    @Override
    public boolean verify(String meta) {
        return meta.toLowerCase().contains(zoneName);
    }

    @Override
    public Class<?> valueType() {
        return String.class;
    }
}
