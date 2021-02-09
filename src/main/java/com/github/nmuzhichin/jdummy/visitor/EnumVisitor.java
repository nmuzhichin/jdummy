package com.github.nmuzhichin.jdummy.visitor;

import java.util.concurrent.ThreadLocalRandom;

final class EnumVisitor extends AbstractMetaValueVisitor {

    EnumVisitor(MetaValueType type) {
        super(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void visitType(Class<?> type) {

        var constants = ((Class<? extends Enum<?>>) type).getEnumConstants();
        if (constants.length > 0) {
            int rangeRandomIdx = ThreadLocalRandom.current().nextInt(0, constants.length);
            valueHolder.setValue(constants[rangeRandomIdx]);
        }
    }
}
