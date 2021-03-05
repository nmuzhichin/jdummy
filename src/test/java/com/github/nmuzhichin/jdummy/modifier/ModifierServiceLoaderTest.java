package com.github.nmuzhichin.jdummy.modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModifierServiceLoaderTest {

    @Test
    void newConverterServiceLoader() {

        var converterServiceLoader = ModifierServiceLoader.load();
        Assertions.assertEquals(3, converterServiceLoader.get(String.class).size());
    }
}