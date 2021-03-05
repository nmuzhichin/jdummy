package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.ElementAccepter;

abstract class AbstractMetaValueVisitor implements Visitor {

    protected final MetaValue metaValue;

    protected final ElementAccepter elementAccepter;

    AbstractMetaValueVisitor(MetaValue metaValue) {
        this.metaValue = metaValue;
        this.elementAccepter = JdummyContext.currentAccepter();
    }
}
