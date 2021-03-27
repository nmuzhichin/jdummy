package com.github.nmuzhichin.mock;

import java.util.List;
import java.util.Set;

public class CircularAttribute {

    private Set<CircularUser> circularUserSet;

    private List<CircularUser> circularUserList;

    public Set<CircularUser> getCircularUserSet() {
        return circularUserSet;
    }

    public List<CircularUser> getCircularUserList() {
        return circularUserList;
    }
}
