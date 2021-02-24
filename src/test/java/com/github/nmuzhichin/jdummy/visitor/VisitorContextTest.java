package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.mock.FullName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VisitorContextTest {

    @Test
    void forElements() {

        var fullName = VisitorContext.currentAccepter().accept(FullName.class);
        Assertions.assertNotNull(fullName);
        Assertions.assertNotNull(fullName.getSurname());
        Assertions.assertNotNull(fullName.getLastName());
        Assertions.assertNotNull(fullName.getFirstName());
    }
}