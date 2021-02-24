package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.Jdummy;
import com.github.nmuzhichin.mock.CircularUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CircularTest {


    @Test
    void circularRun() {

        var user = assertDoesNotThrow(() -> Jdummy.of(CircularUser.class));
        assertEquals(1, user.getCircularAttributes().size());
        assertEquals(4, user.getCircularAttributeList().size());
        user.getCircularAttributes().forEach(it -> assertEquals(1, it.getCircularUserSet().size()));
        user.getCircularAttributeList().forEach(it -> assertEquals(4, it.getCircularUserList().size()));
    }
}
