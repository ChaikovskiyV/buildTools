package com.vchaikovsky.xmlxsdparsing.entity;

public enum StateBankType {
    CENTRAL("central"),
    COMMERCIAL("commercial");
    private String bankType;

    StateBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankType() {
        return bankType;
    }
}