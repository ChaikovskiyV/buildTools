package com.vchaikovsky.xmlparsing.entity;

public enum DepositType {
    DEMAND("demand"),
    FIXED("fixed"),
    PAYMENT("payment"),
    ACCUMULATION("accumulation"),
    METAL("metal"),
    SAVING("saving");
    private String depositType;

    DepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getDepositType() {
        return depositType;
    }
}