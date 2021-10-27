package com.vchaikovsky.xmlxsdparsing.builder;

import com.vchaikovsky.xmlxsdparsing.entity.Bank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.XMLReader;

import java.util.Set;

public class BanksSAXBuilder extends AbstractBanksBuilder{
    static final Logger logger = LogManager.getLogger();
    private BankHandler bankHandler;
    private XMLReader reader;

    public BanksSAXBuilder() {

    }

    public BanksSAXBuilder(Set<Bank> banks, BankHandler bankHandler) {
        super(banks);
        this.bankHandler = bankHandler;
    }

    @Override
    public void buildSetBanks(String filename) {

    }
}
