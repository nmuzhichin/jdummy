package com.github.nmuzhichin.jdummy.modifier;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UuidStringValueModifierTest {

    private final UuidStringValueModifier modifier = new UuidStringValueModifier();

    @Test
    void valueType() {
        assertEquals(String.class, modifier.valueType());
    }

    @Test
    void createValue() {
        assertDoesNotThrow(() -> UUID.fromString(modifier.createValue("uuid")));
    }

    @Test
    void verify() {
        assertTrue(modifier.verify("uuid"));
        assertFalse(modifier.verify("id"));
    }
}