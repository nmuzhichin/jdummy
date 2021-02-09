package com.github.nmuzhichin.jdummy.element;

import com.github.nmuzhichin.mock.FullName;
import com.github.nmuzhichin.mock.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

class ElementsTest {

    @Test
    void newFieldElements() {

        var elementStream = Elements.newFieldElements(User.class);
        Assertions.assertNotNull(elementStream);

        var elements = elementStream.collect(Collectors.toList());
        Assertions.assertEquals(19, elements.size());
    }

    @Test
    void newFieldElements2() {

        var elementStream = Elements.newFieldElements(FullName.class);
        Assertions.assertNotNull(elementStream);

        var elements = elementStream.collect(Collectors.toList());
        Assertions.assertEquals(3, elements.size());
    }
}