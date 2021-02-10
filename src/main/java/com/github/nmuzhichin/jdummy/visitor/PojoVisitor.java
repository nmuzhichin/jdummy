package com.github.nmuzhichin.jdummy.visitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class PojoVisitor extends AbstractMetaValueVisitor {

    private Builder builder = new Builder();

    PojoVisitor(MetaValue type) {
        super(type);
    }

    @Override
    public void visitType(Class<?> type) {
        OverflowGuard.INSTANCE.protect(type);
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
        builder.constructorArgs.add(Visitors.accept(parameter));
    }

    @Override
    public void visitField(Field field) {

        var instance = tryBuild();

        if (OverflowGuard.INSTANCE.underProtect(field.getType())) {
            return;
        }

        try {
            field.setAccessible(true);
            if (field.get(instance) == null) {
                field.set(instance, Visitors.accept(field));
            }
        } catch (Exception e) {
            // skip, field = null
            e.printStackTrace();
        } finally {
            field.setAccessible(false);
        }
    }

    private Object tryBuild() {

        if (builder != null && metaValue.isEmpty()) {
            metaValue.setValue(builder.build());
            builder = null;
        }
        return metaValue.getValue();
    }

    private static class Builder {

        private final List<Object> constructorArgs = new ArrayList<>(4);

        private Function<Object[], Object> constructorInvocation;

        private Object build() {

            try {
                return constructorInvocation.apply(constructorArgs.toArray());
            } finally {
                constructorArgs.clear();
                constructorInvocation = null;
            }
        }
    }
}
