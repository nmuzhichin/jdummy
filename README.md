# Jdummy
Jdummy is the simplest library for creating test objects.

Jdummy provides capabilities:
* creation of complex objects
* creation and filling of collections
* protection against cyclic object instantiation 


Easy to use:

Just call `Jdummy.of(..)` or `Jdummy.manyOf(..)`
```java
class VeryBoringTest {
    // for example a service that doesn't like nullable fields in POJO
    private NullfobicService nullfobicService = new NullfobicService();
    
    void testNullphobicService() {
        User user = Jdummy.of(User.class); // all fields will be present
        Assertions.assertDoesNotThrow(() -> nullfobicService.save(user));
    }
}
```

## Release
In process  