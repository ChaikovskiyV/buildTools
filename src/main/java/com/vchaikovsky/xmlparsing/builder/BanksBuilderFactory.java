package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BanksBuilderFactory {
    static final Logger logger = LogManager.getLogger();

    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }

    private BanksBuilderFactory() {
    }

    public static AbstractBanksBuilder createBanksBuilder(String typeParser) throws BankException {
        if(typeParser.equals(TypeParser.DOM.toString()) && typeParser.equals(TypeParser.SAX.toString()) && typeParser.equals(TypeParser.STAX.toString())) {
            logger.error("The type of parser is null");
            throw new BankException("The type of parser is null");
        }
        TypeParser type = null;
        try {
            type = TypeParser.valueOf(typeParser.toUpperCase());
            return switch (type) {
                case DOM -> new BanksDOMBuilder();
                case SAX -> new BanksSAXBuilder();
                case STAX -> new BanksStAXBuilder();
            };
        } catch (IllegalArgumentException e) {
            logger.error("Unknown type of parser: " + type);
            throw new BankException("Unknown type of parser: " + type);
        }
    }
}