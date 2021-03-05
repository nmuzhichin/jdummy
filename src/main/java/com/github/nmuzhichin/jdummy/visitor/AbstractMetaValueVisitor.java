package com.github.nmuzhichin.jdummy.visitor;

abstract class AbstractMetaValueVisitor implements Visitor {

    protected final MetaValue metaValue;

    protected final VisitorAccepter visitorAccepter;

    AbstractMetaValueVisitor(MetaValue metaValue) {
        this.metaValue = metaValue;
        this.visitorAccepter = VisitorContext.currentAccepter();
    }
}
