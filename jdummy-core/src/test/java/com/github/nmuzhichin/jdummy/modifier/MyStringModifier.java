package com.github.nmuzhichin.jdummy.modifier;

public class MyStringModifier extends ValueModifier<String> {

    @Override
    public Class<String> valueType() {
        return String.class;
    }

    @Override
    public String createValue(String meta) {
        return "oops!";
    }

    @Override
    public boolean verify(String meta) {
        return false;
    }
}
