# Jdummy
The Jdummy is the simplest library for creating test objects.

Easy to use
```java
class FullName {
    // ...
    private String firstName;
    /* methods omitted */
}

class User {
    // ...
    private FullName fullName;
    /* methods omitted */
}

class NullfobicService {
    
    void save(User user) {
        String lastName = user.getFullName().getLastName(); // npe?
        // ...
    }
}
```

Just call `Jdummy.of(..)` or `Jdummy.manyOf(..)`
```java
class VeryBoringTest {
    
    private NullfobicService nullfobicService = new NullfobicService();
    
    // for example a service that doesn't like nullable fields in POJO
    void testNullphobicService() {
        User user = Jdummy.of(User.class); // all fields will be present
        Assertions.assertDoesNotThrow(() -> nullfobicService.save(user));
    }
}
```

# Release
In process  