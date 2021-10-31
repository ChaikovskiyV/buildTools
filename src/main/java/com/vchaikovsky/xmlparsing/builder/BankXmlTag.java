package com.vchaikovsky.xmlparsing.builder;

public enum BankXmlTag {
    BANKS("banks"),
    STATE_BANK("state-bank"),
    PRIVATE_BANK("private-bank"),
    DEPOSIT("deposit"),
    NAME("name"),
    COUNTRY("country"),
    BANK_TYPE("bank-type"),
    STATE_SHARE("state-share"),
    TYPE("type"),
    DEPOSITOR("depositor"),
    ACCOUNT_ID("account-id"),
    AMOUNT_ON_DEPOSIT("amount-on-deposit"),
    PROFITABILITY("profitability"),
    TIME_CONSTRAINTS("time-constraints");

    private String value;

    BankXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}