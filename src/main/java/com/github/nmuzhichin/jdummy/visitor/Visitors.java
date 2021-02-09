package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.element.Elements;

public final class Visitors {

    private Visitors() {
        // use static methods
    }

    @SuppressWarnings("unchecked")
    public static <T> T forElements(MetaValueType type) {

        try {
            var elements = Elements.newAllElements(type.getClassType());
            for (var e : elements) {
                e.accept(type.getVisitor());
            }
            return (T) type.getValueHolder().getValue();
        } finally {
            OverflowGuard.INSTANCE.unprotect(type.getClassType());
        }
    }
}
