package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.mock.FullName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JdummyContextTest {

    @Test
    void forElements() {

        var fullName = JdummyContext.currentAccepter().accept(FullName.class);
        Assertions.assertNotNull(fullName);
        Assertions.assertNotNull(fullName.getSurname());
        Assertions.assertNotNull(fullName.getLastName());
        Assertions.assertNotNull(fullName.getFirstName());
    }
}