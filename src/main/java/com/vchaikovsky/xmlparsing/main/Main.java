package com.vchaikovsky.xmlparsing.main;

import com.vchaikovsky.xmlparsing.builder.AbstractBanksBuilder;
import com.vchaikovsky.xmlparsing.builder.BanksBuilderFactory;
import com.vchaikovsky.xmlparsing.exception.BankException;
import com.vchaikovsky.xmlparsing.validator.BankValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static final Logger logger = LogManager.getLogger();
    static final String FILE_NAME = "sources/banks.xml";
    static final String SAX_TYPE_PARSER = "SAX";
    static final String DOM_TYPE_PARSER = "DOM";
    static final String STAX_TYPE_PARSER = "STAX";

    public static void main(String[] args) throws BankException {
        if(BankValidator.validateXMLFile(FILE_NAME)) {
            //SAX
            AbstractBanksBuilder saxBuilder = BanksBuilderFactory.createBanksBuilder(SAX_TYPE_PARSER);//new BanksSAXBuilder();
            saxBuilder.buildSetBanks(FILE_NAME);
            saxBuilder.getBanks().forEach(logger :: info);
            System.out.println("\nlength = " + saxBuilder.getBanks().size());

            //DOM
            AbstractBanksBuilder domBuilder = BanksBuilderFactory.createBanksBuilder(DOM_TYPE_PARSER);//new BanksDOMBuilder();
            domBuilder.buildSetBanks(FILE_NAME);
            domBuilder.getBanks().forEach(logger :: info);
            System.out.println("\nlength = " + domBuilder.getBanks().size());

            //StAX
            AbstractBanksBuilder stAXBuilder = BanksBuilderFactory.createBanksBuilder(STAX_TYPE_PARSER);
            stAXBuilder.buildSetBanks(FILE_NAME);
            stAXBuilder.getBanks().forEach(logger :: info);
            System.out.println("\nlength = " + stAXBuilder.getBanks().size());
        }
    }
}