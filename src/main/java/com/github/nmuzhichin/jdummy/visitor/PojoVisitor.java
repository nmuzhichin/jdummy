package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.cache.CacheWriter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class PojoVisitor extends AbstractMetaValueVisitor {

    private Builder builder;

    private VisitorAccepter accepter;

    private CacheWriter cacheWriter;

    PojoVisitor(MetaValue type) {
        super(type);
        this.builder = new Builder();
        this.accepter = VisitorContext.currentAccepter();
        this.cacheWriter = VisitorContext.currentCacheWriter();
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
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public void visitParameter(Parameter parameter) {
        builder.constructorArgs.add(accepter.accept(parameter));
    }

    @Override
    public void visitField(Field field) {

        if (!cacheWriter.contains(field.getType())) {
            try {
                var instance = tryBuild();
                field.setAccessible(true);
                if (field.get(instance) == null) {
                    field.set(instance, accepter.accept(field));
                }
            } catch (Exception e) {
                // skip, field = null
                // todo add log
                e.printStackTrace();
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
