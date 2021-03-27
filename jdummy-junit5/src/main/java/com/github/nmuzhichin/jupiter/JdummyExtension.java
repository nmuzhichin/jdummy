package com.github.nmuzhichin.jupiter;

import com.github.nmuzhichin.jdummy.Jdummy;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class JdummyExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {

        return parameterContext.getDeclaringExecutable().isAnnotationPresent(JdummySource.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
        throws ParameterResolutionException {

        return Jdummy.of(parameterContext.getParameter().getType());
    }
}
