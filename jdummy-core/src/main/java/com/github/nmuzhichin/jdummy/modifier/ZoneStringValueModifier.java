package com.github.nmuzhichin.jdummy.modifier;

import java.util.concurrent.ThreadLocalRandom;

public class ZoneStringValueModifier extends ValueModifier<String> {

    private static final String zoneName = "zone";

    @Override
    public String createValue(String meta) {
        int id = ThreadLocalRandom.current().nextInt(-12, 15);
        return id >= 0 ? "+" + id : String.valueOf(id);
    }

    @Override
    public boolean verify(String meta) {
        return meta.toLowerCase().contains(zoneName);
    }

    @Override
    public Class<String> valueType() {
        return String.class;
    }
}
