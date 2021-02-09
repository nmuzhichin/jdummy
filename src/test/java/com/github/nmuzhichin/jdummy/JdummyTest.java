package com.github.nmuzhichin.jdummy;

import com.github.nmuzhichin.mock.RoleUserMap;
import com.github.nmuzhichin.mock.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;

class JdummyTest {

    @ParameterizedTest
    @ValueSource(classes = {
            Boolean.class, Byte.class, Short.class,
            Character.class, Integer.class, Long.class,
            Float.class, Double.class, boolean.class, byte.class,
            short.class, char.class, int.class, long.class,
            float.class, double.class
    })
    void createPrimitive(Class<?> type) {

        var primitive = Jdummy.of(type);
        Assertions.assertNotNull(primitive);
    }

    @ParameterizedTest
    @ValueSource(classes = {
            Boolean.class, Byte.class, Short.class,
            Character.class, Integer.class, Long.class,
            Float.class, Double.class, boolean.class, byte.class,
            short.class, char.class, int.class, long.class,
            float.class, double.class
    })
    void createPrimitiveStream(Class<?> type) {

        var primitiveStream = Jdummy.manyOf(type);
        Assertions.assertNotNull(primitiveStream);
        Assertions.assertTrue(primitiveStream.limit(100_000).allMatch(Objects::nonNull));
    }

    @Test
    void createOne() {

        var user = Jdummy.of(User.class);
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getAge());
        Assertions.assertNotNull(user.getClassification());
        Assertions.assertNotNull(user.getDescription());
        Assertions.assertNotNull(user.getFullName());
        Assertions.assertNotNull(user.getFullName().getFirstName());
        Assertions.assertNotNull(user.getFullName().getLastName());
        Assertions.assertNotNull(user.getFullName().getSurname());
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getPrimaryRole());
        Assertions.assertNotNull(user.getRoles());
        Assertions.assertNotNull(user.getItemsId());
        Assertions.assertNotNull(user.getZones());
        Assertions.assertNotNull(user.getCreateTime());
        Assertions.assertNotNull(user.getZonedDateTime());
        Assertions.assertNotNull(user.getPrincipalId());
        Assertions.assertNotNull(user.getLogin());
        Assertions.assertNotNull(user.getPassHash());
        Assertions.assertNotNull(user.getLastAccessTimestamp());
        Assertions.assertNull(user.getBoss()); // todo
    }

    @Test
    void createComplex() {

        var roleUserMap = Jdummy.of(RoleUserMap.class);
        Assertions.assertNotNull(roleUserMap);
        Assertions.assertTrue(roleUserMap.getUserEnumMap().size() > 0);
        roleUserMap.getUserEnumMap().values().forEach(Assertions::assertNotNull);
    }

    @Test
    void createMany() {

        var userStream = Jdummy.manyOf(User.class);
        Assertions.assertTrue(userStream.limit(10).allMatch(Objects::nonNull));
    }
}