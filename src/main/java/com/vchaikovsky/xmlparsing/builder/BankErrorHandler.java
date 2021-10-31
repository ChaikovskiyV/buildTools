package com.vchaikovsky.xmlparsing.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class BankErrorHandler implements ErrorHandler {
    static final Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) {
        logger.warn(getErrorPosition(exception) + ": " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) {
        logger.warn(getErrorPosition(exception) + ": " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) {
        logger.warn(getErrorPosition(exception) + ": " + exception.getMessage());
    }

    private String getErrorPosition(SAXParseException exception) {
        return "line " + exception.getLineNumber() + ", column " + exception.getColumnNumber();
    }
}