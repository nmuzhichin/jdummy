package com.github.nmuzhichin.jdummy.modifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class NameValueModifier extends ValueModifier {

    private final List<String> names;

    public NameValueModifier() {
        this.names = fileLoad();
    }

    private List<String> fileLoad() {

        var is = this.getClass().getClassLoader().getResourceAsStream("names.csv");
        if (is == null) {
            return List.of();
        }
        var bufferedReader = new BufferedReader(new InputStreamReader(is));
        return bufferedReader.lines().collect(Collectors.toList());
    }

    @Override
    public Class<?> valueType() {
        return String.class;
    }

    @Override
    <T> T createValue(String meta) {
        if (names.size() == 0) {
            return (T) "jdummy";
        }
        int idx = ThreadLocalRandom.current().nextInt(0, names.size());
        return (T) names.get(idx);
    }

    @Override
    boolean verify(String meta) {
        return meta.toLowerCase().contains("name");
    }
}
