package com.vchaikovsky.xmlparsing.builder;

import com.vchaikovsky.xmlparsing.exception.BankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BanksBuilderFactoryTest {
    static final Logger logger = LogManager.getLogger();
    private AbstractBanksBuilder builder;
    private String sax;
    private String dom;
    private String stax;
    private String wrong;

    @BeforeAll
    void setUp() {
        logger.info("The testing is starting...");
        sax = "sax";
        dom = "dom";
        stax = "stax";
        wrong = "wrong";
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void createSAXBanksBuilder() throws BankException {
        builder = BanksBuilderFactory.createBanksBuilder(sax);

        assertTrue(builder instanceof BanksSAXBuilder);
    }

    @Test
    public void createDOMBanksBuilder() throws BankException {
        builder = BanksBuilderFactory.createBanksBuilder(dom);

        assertTrue(builder instanceof BanksDOMBuilder);
    }

    @Test
    public void createStAXBanksBuilder() throws BankException {
        builder = BanksBuilderFactory.createBanksBuilder(stax);

        assertTrue(builder instanceof BanksStAXBuilder);
    }

    @Test
    public void whetherThrowExceptionIfParameterWrong() {
        assertThrows(BankException.class, () -> BanksBuilderFactory.createBanksBuilder(wrong));
    }
}