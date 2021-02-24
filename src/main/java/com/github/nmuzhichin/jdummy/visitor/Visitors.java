package com.github.nmuzhichin.jdummy.visitor;

public final class Visitors {

    private Visitors() {
        // use statics methods
    }

    static Visitor findVisitor(Class<?> clazz, MetaValue type) {

        Visitor visitor;
        if (Reflections.isPrimitiveOrWrapper(clazz)) {
            visitor = new PrimitiveVisitor(type);
        } else if (clazz.isEnum()) {
            visitor = new EnumVisitor(type);
        } else if (clazz.isArray()) {
            visitor = new ArrayVisitor(type);
        } else if (Reflections.isCollectionsOrMap(clazz)) {
            visitor = new CollectionVisitor(type);
        } else if (Reflections.isJavaLibraryType(clazz)) {
            visitor = new JavaCoreVisitor(type);
        } else {
            visitor = new PojoVisitor(type);
        }
        return visitor;
    }
}
