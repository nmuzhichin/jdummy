package com.github.nmuzhichin.jdummy.modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class NameValueModifier extends ValueModifier<String> {

    private static final Logger log = LoggerFactory.getLogger(NameValueModifier.class);

    private final List<String> names;

    public NameValueModifier() {
        this.names = fileLoad();
    }

    private List<String> fileLoad() {

        try (var is = this.getClass().getClassLoader().getResourceAsStream("names.csv")) {
            if (is == null) {
                return List.of();
            }
            var bufferedReader = new BufferedReader(new InputStreamReader(is));
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
            return List.of();
        }
    }

    @Override
    public Class<String> valueType() {
        return String.class;
    }

    @Override
    public String createValue(String meta) {
        if (names.size() == 0) {
            return "jdummy";
        }
        int idx = ThreadLocalRandom.current().nextInt(0, names.size());
        return names.get(idx);
    }

    @Override
    public boolean verify(String meta) {
        return meta.toLowerCase().contains("name");
    }
}
