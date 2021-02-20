package com.github.nmuzhichin.jdummy.modify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ModifierServiceLoaderTest {

    @Test
    void newConverterServiceLoader() {

        var converterServiceLoader = ModifierServiceLoader.newServiceLoader();
        Assertions.assertEquals(1, converterServiceLoader.openValueModifierStream().count());
    }
}