package com.github.nmuzhichin.mock;

import java.math.BigInteger;

public abstract class Principal extends LogPassHash {

    private BigInteger principalId;

    public BigInteger getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(BigInteger principalId) {
        this.principalId = principalId;
    }
}
