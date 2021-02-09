package com.github.nmuzhichin.jdummy.element;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.reflect.Modifier.isPublic;
import static java.util.Arrays.stream;

public final class Elements {

    private Elements() {
        // use static methods
    }

    public static List<Element> newAllElements(Class<?> type) {

        var list = new ArrayList<Element>();
        list.add(newTypeElement(type));
        var constructorElement = newConstructorElement(type);
        if (!(constructorElement instanceof NullElement)) {
            var constructor = ((ConstructorElement) constructorElement).getConstructor();
            list.add(constructorElement);
            list.addAll(newParameterElements(constructor));
        }
        list.addAll(newFieldElements(type).collect(Collectors.toUnmodifiableList()));
        return list;
    }

    public static Element newTypeElement(Class<?> type) {

        return new TypeElement<>(type);
    }

    public static Element newConstructorElement(Class<?> type) {

        return stream(type.getDeclaredConstructors())
                .filter(c -> isPublic(c.getModifiers()))
                .max(Comparator.comparingInt(Constructor::getParameterCount))
                .<Element>map(ConstructorElement::new)
                .orElse(NullElement.NULL_ELEMENT);
    }

    public static List<Element> newParameterElements(Constructor<?> constructor) {

        return stream(constructor.getParameters())
                .map(ParameterElement::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public static Stream<Element> newFieldElements(Class<?> type) {

        if (type == null) {
            return Stream.empty();
        }

        Stream<Element> stream = type.getSuperclass() != Object.class
                ? newFieldElements(type.getSuperclass())
                : Stream.empty();

        return Stream.concat(stream, stream(type.getDeclaredFields())
                .map(FieldElement::new));
    }
}
