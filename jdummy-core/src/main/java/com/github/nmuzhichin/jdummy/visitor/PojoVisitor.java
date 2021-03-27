package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.CacheWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class PojoVisitor extends AbstractMetaValueVisitor {

    private static final Logger log = LoggerFactory.getLogger(PojoVisitor.class);

    private final CacheWriter cacheWriter;

    private Builder builder;

    PojoVisitor(MetaValue type) {
        super(type);
        this.cacheWriter = JdummyContext.currentCacheWriter();
        this.builder = new Builder();
    }

    @Override
    public void visitType(TypeElement element) {
        builder.setType(element.getUnderlying());
    }

    @Override
    public void visitConstructor(ConstructorElement element) {
        builder.constructorInvocation = arg -> {
            try {
                return element.getUnderlying().newInstance(arg);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error(e.getMessage(), e);
                }
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public void visitParameter(ParameterElement element) {
        builder.constructorArgs.add(elementAccepter.accept(element.getUnderlying()));
    }

    @Override
    public void visitField(FieldElement element) {

        var field = element.getUnderlying();
        if (!cacheWriter.contains(field.getType())) {
            try {
                var instance = tryBuild();
                field.setAccessible(true);
                if (field.get(instance) == null) {
                    field.set(instance, elementAccepter.accept(field));
                }
            } catch (Exception e) {
                // skip, field = null
                if (log.isErrorEnabled()) {
                    log.error(e.getMessage(), e);
                }
            } finally {
                field.setAccessible(false);
            }
        }
    }

    private Object tryBuild() {

        if (builder != null && metaValue.isEmpty()) {
            var value = builder.build();
            cacheWriter.write(builder.type, value);
            metaValue.setValue(value);
            builder = null;
        }
        return metaValue.getValue();
    }

    private static class Builder {

        private final List<Object> constructorArgs = new ArrayList<>(4);

        private Function<Object[], Object> constructorInvocation;

        private Class<?> type;

        private Object build() {

            try {
                return constructorInvocation.apply(constructorArgs.toArray());
            } finally {
                constructorArgs.clear();
                constructorInvocation = null;
            }
        }

        public void setType(Class<?> type) {
            this.type = type;
        }
    }
}
