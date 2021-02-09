package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.misc.Reflections;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

final class PrimitiveVisitor extends AbstractMetaValueVisitor {

    private final Random random = ThreadLocalRandom.current();

    PrimitiveVisitor(MetaValueType type) {
        super(type);
    }

    @Override
    public void visitType(Class<?> type) {

        Class<?> primitiveType = Reflections.primitiveUnwrap(type);
        if (primitiveType == Void.TYPE) {
            visitAsVoid();
        } else if (primitiveType == Boolean.TYPE) {
            visitAsBoolean();
        } else if (primitiveType == Byte.TYPE) {
            visitAsByte();
        } else if (primitiveType == Short.TYPE) {
            visitAsShort();
        } else if (primitiveType == Character.TYPE) {
            visitAsCharacter();
        } else if (primitiveType == Integer.TYPE) {
            visitAsInteger();
        } else if (primitiveType == Long.TYPE) {
            visitAsLong();
        } else if (primitiveType == Float.TYPE) {
            visitAsFloat();
        } else if (primitiveType == Double.TYPE) {
            visitAsDouble();
        }
    }

    private void visitAsVoid() {
        valueHolder.setValue(null);
    }

    private void visitAsBoolean() {
        valueHolder.setValue(random.nextBoolean());
    }

    private void visitAsByte() {
        valueHolder.setValue((byte) random.nextInt(Byte.MAX_VALUE));
    }

    private void visitAsShort() {
        valueHolder.setValue((short) random.nextInt(Short.MAX_VALUE));
    }

    private void visitAsCharacter() {
        valueHolder.setValue((char) random.nextInt(Character.MAX_VALUE));
    }

    private void visitAsInteger() {
        valueHolder.setValue(random.nextInt());
    }

    private void visitAsLong() {
        valueHolder.setValue(random.nextLong());
    }

    private void visitAsFloat() {
        valueHolder.setValue(random.nextFloat());
    }

    private void visitAsDouble() {
        valueHolder.setValue(random.nextDouble());
    }
}
