package com.github.nmuzhichin.jdummy.modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NameValueModifierTest {

    private final NameValueModifier modifier = new NameValueModifier();

    @Test
    void valueType() {

        Assertions.assertEquals(String.class, modifier.valueType());
    }

    @Test
    void createValue() {

        var name = modifier.createValue("name");
        assertNotNull(name);
        assertFalse(name.matches("([0-9])+"));
    }

    @Test
    void verify() {
        Assertions.assertFalse(modifier.verify("na"));
        Assertions.assertTrue(modifier.verify("name"));
    }
}