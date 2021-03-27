package com.github.nmuzhichin.jupiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(JdummyExtension.class)
class JdummyExtensionTest {

    @Test
    @JdummySource
    void generateInteger(Integer value) {
        Assertions.assertNotNull(value, "Value must be generated");
    }

    @Test
    @JdummySource
    void generateIntegerString(Integer value, String string) {
        Assertions.assertNotNull(value, "Value must be generated");
        Assertions.assertNotEquals(0, string.length(), "String length must be more then zero");
    }

    @Test
    @JdummySource
    void generatePojo(Pojo pojo) {
        Assertions.assertNotNull(pojo);
        Assertions.assertNotNull(pojo.a);
        Assertions.assertNotNull(pojo.b);
        Assertions.assertNotNull(pojo.c);
    }

    public static class Pojo {

        private Byte a;

        private Long b;

        private String c;
    }
}