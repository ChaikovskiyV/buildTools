package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.exception.HandingException;
import com.vchaikovsky.informationhanding.reader.TextReaderFromFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpressionParserTest {
    static final Logger logger = LogManager.getLogger();
    @Spy
    private TextReaderFromFile reader;
    private ExpressionParser parser;
    private String expression;
    private String expected;
    private TextComponent result;

    @BeforeAll
    void setUp() throws HandingException {
        logger.info("The testing is starting...");
        MockitoAnnotations.openMocks(this);
        parser = new ExpressionParser();
        expression = "1^(2&5|2)";
        Mockito.doReturn(expression)
                .when(reader)
                .readText(Mockito.any());
        expected = " 3";
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void parse() {
        result = parser.parse(expression);

        assertEquals(expected, result.toString());
    }
}