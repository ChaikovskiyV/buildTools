package com.vchaikovsky.xmlxsdparsing.factory;

import com.vchaikovsky.xmlxsdparsing.builder.AbstractDepositsBuilder;
import com.vchaikovsky.xmlxsdparsing.builder.DepositDOMBuilder;
import com.vchaikovsky.xmlxsdparsing.builder.DepositSAXBuilder;
import com.vchaikovsky.xmlxsdparsing.builder.DepositStAXBuilder;
import com.vchaikovsky.xmlxsdparsing.exception.DepositException;

public class DepositBuilderFactory {
    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }

    private DepositBuilderFactory() {
    }

    public static AbstractDepositsBuilder createDepositsBuilder(String typeParser) {
        if(typeParser.toUpperCase().equals(TypeParser.DOM) && typeParser.toUpperCase().equals(TypeParser.SAX) && typeParser.toUpperCase().equals(TypeParser.STAX)){

        }
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());

        switch (type) {
            case DOM: return new DepositDOMBuilder();
            case SAX: return new DepositSAXBuilder();
            case STAX: return new DepositStAXBuilder();
            default: new DepositException();
        }
        return null;
    }
}
