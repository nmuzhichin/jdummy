package com.github.nmuzhichin.jdummy.visitor;

/**
 * @author nmuzhichin
 * @since 08.02.2021
 */
interface Visitor {

    default void visit(Element e) {
    }

    default void visitType(TypeElement e) {
    }

    default void visitConstructor(ConstructorElement e) {
    }

    default void visitParameter(ParameterElement e) {
    }

    default void visitField(FieldElement e) {
    }
}
