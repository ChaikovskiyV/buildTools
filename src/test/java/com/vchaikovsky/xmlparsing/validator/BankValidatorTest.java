package com.vchaikovsky.xmlparsing.validator;

import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankValidatorTest {
    static final Logger logger = LogManager.getLogger();
    private String xmlFileName;
    private String wrongXmlFileName;
    private String wrongFileName;
    private boolean result;

    @BeforeAll
    void setUp() {
        logger.info("The testing is starting...");
        xmlFileName = "sources/bankstest.xml";
        wrongXmlFileName = "sources/wrongxmltest.xml";
        wrongFileName = "source.xml";

    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void validateXMLFile() throws BankException {
        result = BankValidator.validateXMLFile(xmlFileName);

        assertTrue(result);
    }

    @Test
    public void ifXMLFileNotValid() throws BankException {
        result = BankValidator.validateXMLFile(wrongXmlFileName);

        assertFalse(result);
    }

    @Test
    public void whetherThrowExceptionIfFileNotFound() {
        assertThrows(BankException.class, () -> BankValidator.validateXMLFile(wrongFileName));
    }
}