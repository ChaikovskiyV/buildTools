package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.entity.Bank;
import com.vchaikovsky.xmlparsing.exception.BankException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBanksBuilder {
    protected Set<Bank> banks;

    public AbstractBanksBuilder() {
        this.banks = new HashSet<>();
    }

    public AbstractBanksBuilder(Set<Bank> banks) {
        this.banks = banks;
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    abstract public void buildSetBanks(String filename) throws BankException;
}