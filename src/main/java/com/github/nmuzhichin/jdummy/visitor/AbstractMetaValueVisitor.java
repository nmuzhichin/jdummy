package com.github.nmuzhichin.jdummy.visitor;

abstract class AbstractMetaValueVisitor implements Visitor {

    protected final MetaValue metaValue;

    AbstractMetaValueVisitor(MetaValue metaValue) {
        this.metaValue = metaValue;
    }
}
