package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.cache.CacheWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class PojoVisitor extends AbstractMetaValueVisitor {

    private static final Logger log = LoggerFactory.getLogger(PojoVisitor.class);

    private final CacheWriter cacheWriter;

    private Builder builder;

    PojoVisitor(MetaValue type) {
        super(type);
        this.cacheWriter = VisitorContext.currentCacheWriter();
        this.builder = new Builder();
    }

    @Override
    public void visitType(Class<?> type) {
        builder.setType(type);
    }

    @Override
    public void visitConstructor(Constructor<?> constructor) {
        builder.constructorInvocation = arg -> {
            try {
                return constructor.newInstance(arg);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error(e.getMessage(), e);
                }
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public void visitParameter(Parameter parameter) {
        builder.constructorArgs.add(visitorAccepter.accept(parameter));
    }

    @Override
    public void visitField(Field field) {

        if (!cacheWriter.contains(field.getType())) {
            try {
                var instance = tryBuild();
                field.setAccessible(true);
                if (field.get(instance) == null) {
                    field.set(instance, visitorAccepter.accept(field));
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
