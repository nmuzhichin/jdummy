package com.github.nmuzhichin.jdummy.modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ModifierServiceLoaderTest {

    @Test
    void newConverterServiceLoader() {

        var converterServiceLoader = new ModifierServiceLoader().getModifiers();
        // 4 predefined + 2 custom in test
        Assertions.assertEquals(6, converterServiceLoader.values().stream().mapToLong(List::size).sum());
    }
}