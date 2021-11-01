package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class BanksSAXBuilder extends AbstractBanksBuilder{
    static final Logger logger = LogManager.getLogger();
    private BankHandler bankHandler;
    private BankErrorHandler bankErrorHandler;
    private XMLReader reader;


    public BanksSAXBuilder() throws BankException {
        bankHandler = new BankHandler();
        bankErrorHandler = new BankErrorHandler();
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(bankHandler);
            reader.setErrorHandler(bankErrorHandler);
        } catch (ParserConfigurationException e) {
            logger.error("Exception from parser configuration.", e);
            throw new BankException("Exception from parser configuration.", e);
        } catch (SAXException e) {
            logger.error("Exception from SAX parser.", e);
            throw new BankException("Exception from SAX parser.", e);
        }
    }

    @Override
    public void buildSetBanks(String filename) throws BankException {
        try {
            reader.parse(filename);
        } catch (IOException e) {
            logger.error("The file " + filename + " was not found.", e);
            throw new BankException("The file " + filename + " was not found.", e);
        } catch (SAXException e) {
            logger.error("Exception from SAX parser.", e);
            throw new BankException("Exception from SAX parser.", e);
        }
        banks = bankHandler.getBanks();
    }
}