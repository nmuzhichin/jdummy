package com.github.nmuzhichin.jdummy.visitor;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

final class PrimitiveVisitor extends AbstractMetaValueVisitor {

    private final Random random = ThreadLocalRandom.current();

    PrimitiveVisitor(MetaValue type) {
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
        metaValue.setValue(null);
    }

    private void visitAsBoolean() {
        metaValue.setValue(random.nextBoolean());
    }

    private void visitAsByte() {
        metaValue.setValue((byte) random.nextInt(Byte.MAX_VALUE));
    }

    private void visitAsShort() {
        metaValue.setValue((short) random.nextInt(Short.MAX_VALUE));
    }

    private void visitAsCharacter() {
        metaValue.setValue((char) random.nextInt(Character.MAX_VALUE));
    }

    private void visitAsInteger() {
        metaValue.setValue(random.nextInt());
    }

    private void visitAsLong() {
        metaValue.setValue(random.nextLong());
    }

    private void visitAsFloat() {
        metaValue.setValue(random.nextFloat());
    }

    private void visitAsDouble() {
        metaValue.setValue(random.nextDouble());
    }
}
