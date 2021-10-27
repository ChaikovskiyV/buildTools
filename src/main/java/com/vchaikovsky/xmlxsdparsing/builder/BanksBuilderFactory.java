package com.vchaikovsky.xmlxsdparsing.builder;

import com.vchaikovsky.xmlxsdparsing.exception.BankException;
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

    public static AbstractBanksBuilder createBankBuilder(String typeParser) throws BankException {
        if(typeParser.equals(TypeParser.DOM) && typeParser.equals(TypeParser.SAX) && typeParser.equals(TypeParser.STAX)){
            logger.error("The type of parser is null");
            throw new BankException("The type of parser is null");
        }

        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());

        return switch (type) {
            case DOM -> new BanksDOMBuilder();
            case SAX -> new BanksSAXBuilder();
            case STAX -> new BanksStAXBuilder();
            default->{
                logger.error("Unknown type of parser: " + type);
                throw new BankException("Unknown type of parser: " + type);
            }
        };
    }
}