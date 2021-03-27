package com.github.nmuzhichin.jdummy.modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumericStringValueModifierTest {

    private final NumericStringValueModifier modifier = new NumericStringValueModifier();

    @Test
    void createValue() {
        var v = modifier.createValue("");
        Assertions.assertNotNull(v);
        Assertions.assertTrue(v.matches("([-+]?)([0-9])+"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"arg", "age", "number", "position", "page", "int", "long"})
    void verify(String meta) {
        Assertions.assertTrue(modifier.verify(meta));
    }

    @Test
    void verify() {
        Assertions.assertFalse(modifier.verify("id"));
    }

    @Test
    void valueType() {
        Assertions.assertEquals(String.class, modifier.valueType());
    }
}