## About Jdummy
Jdummy is the simplest library for creating test objects.

Jdummy provides capabilities:
* creation of complex objects
* protection against cyclic object instantiation
* fields' modifiers for more logical and contextual values
* creation and populate of collections

Easy to use:

Just call `Jdummy.of(..)`
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

or use junit5 extension
```java
@ExtendWith(JdummyExtension.class)
class JdummyExtensionTest {

    @Test
    @JdummySource
    void generateInteger(Integer value) {
        Assertions.assertNotNull(value, "Value must be generated");
    }
}
```

### Download
Maven
```xml
<dependency>
    <groupId>com.github.nmuzhichin</groupId>
    <artifactId>jdummy-core</artifactId>
    <version>1.1.0</version>
    <scope>test</scope>
</dependency>
<!-- For junit-jupiter extension -->
<dependency>
    <groupId>com.github.nmuzhichin</groupId>
    <artifactId>jdummy-junit5</artifactId>
    <version>1.1.0</version>
    <scope>test</scope>
</dependency>
```
Gradle
```groovy
testImplementation 'com.github.nmuzhichin:jdummy-core:1.1.0'
// For junit-jupiter extension
testImplementation 'com.github.nmuzhichin:jdummy-junit5:1.1.0'
```

### Advanced features

##### Cache
If you need to use a ready-made object to create an instance, 
just call `Jdummy.putCache (...)` before that.   

##### Custom modifiers
Each subject area has its own characteristics, which can be reflected in the 
names of the fields and their values.

For this, `Jdummy` contains a `ValueModifier` type that is used to customize the values. 
You can declare your own `ValueModifier` type and write it to a custom 
`META-INFO/services/com.github.nmuzhichin.jdummy.modifier.ValueModifier` 
for loading by `ServiceLoader`.