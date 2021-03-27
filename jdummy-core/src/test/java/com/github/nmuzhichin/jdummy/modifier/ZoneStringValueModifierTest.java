package com.github.nmuzhichin.jdummy.modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ZoneStringValueModifierTest {

    private static final Logger log = LoggerFactory.getLogger(ZoneStringValueModifierTest.class);

    private final ZoneStringValueModifier modifier = new ZoneStringValueModifier();

    @RepeatedTest(42)
    void createValue() {
        var v = modifier.createValue("");
        Assertions.assertNotNull(v);
        log.debug("ZoneId {}", v);
        Assertions.assertTrue(v.matches("([+-])([0-9]){1,2}"));
    }

    @Test
    void verify() {
    }

    @Test
    void valueType() {
    }
}