package com.github.nmuzhichin.jdummy.modifier;

public class MyIntModifier extends ValueModifier<Integer> {

    @Override
    public Class<Integer> valueType() {
        return Integer.class;
    }

    @Override
    public Integer createValue(String meta) {
        return 1;
    }

    @Override
    public boolean verify(String meta) {
        return false;
    }
}
