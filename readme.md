# Jdummy
The Jdummy is the simplest library for creating test objects.

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

# Release
In process  