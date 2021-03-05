package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.Jdummy;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

final class ArrayVisitor extends AbstractMetaValueVisitor {

    private final Random random = ThreadLocalRandom.current();

    ArrayVisitor(MetaValue type) {
        super(type);
    }

    @Override
    public void visitType(TypeElement element) {
        var type = element.getUnderlying();
        if (type == boolean[].class) {
            visitAsBooleanArray();
        } else if (type == byte[].class) {
            visitAsByteArray();
        } else if (type == short[].class) {
            visitAsShortArray();
        } else if (type == char[].class) {
            visitAsCharacterArray();
        } else if (type == int[].class) {
            visitAsIntegerArray();
        } else if (type == long[].class) {
            visitAsLongArray();
        } else if (type == float[].class) {
            visitAsFloatArray();
        } else if (type == double[].class) {
            visitAsDoubleArray();
        } else {
            visitAsArray(type);
        }
    }

    private void visitAsBooleanArray() {
        metaValue.setValue(new boolean[]{true, false});
    }

    private void visitAsByteArray() {
        var bytes = new byte[8];
        random.nextBytes(bytes);
        metaValue.setValue(bytes);
    }

    private void visitAsShortArray() {
        var shorts = new short[8];
        for (int i = 0; i < shorts.length; i++) {
            shorts[i] = (short) random.nextInt(Short.MAX_VALUE);
        }
        metaValue.setValue(shorts);
    }

    private void visitAsCharacterArray() {
        var chars = new char[8];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) random.nextInt(Character.MAX_VALUE);
        }
        metaValue.setValue(chars);
    }

    private void visitAsIntegerArray() {
        metaValue.setValue(random.ints(8).toArray());
    }

    private void visitAsLongArray() {
        metaValue.setValue(random.longs(8).toArray());
    }

    private void visitAsFloatArray() {
        float[] floats = new float[8];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = random.nextFloat();
        }
        metaValue.setValue(floats);
    }

    private void visitAsDoubleArray() {
        metaValue.setValue(random.doubles(8).toArray());
    }

    private void visitAsArray(Class<?> cls) {

        var componentType = cls.getComponentType();
        var array = Array.newInstance(componentType, 8);
        for (int i = 0; i < 8; i++) {
            Array.set(array, i, Jdummy.of(componentType));
        }
        metaValue.setValue(array);
    }
}
