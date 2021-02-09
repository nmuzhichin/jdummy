package com.github.nmuzhichin.mock;

import java.util.EnumMap;

public class RoleUserMap {

    private EnumMap<Role, User> userEnumMap;

    public EnumMap<Role, User> getUserEnumMap() {
        return userEnumMap;
    }

    public void setUserEnumMap(EnumMap<Role, User> userEnumMap) {
        this.userEnumMap = userEnumMap;
    }
}
