package com.github.nmuzhichin.mock;

import java.util.List;
import java.util.Set;

public class CircularUser {

    private Set<CircularAttribute> circularAttributes;

    private List<CircularAttribute> circularAttributeList;

    public Set<CircularAttribute> getCircularAttributes() {
        return circularAttributes;
    }

    public List<CircularAttribute> getCircularAttributeList() {
        return circularAttributeList;
    }
}
