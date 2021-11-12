package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.exception.HandingException;
import com.vchaikovsky.informationhanding.reader.TextReaderFromFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParagraphParserTest {
    static final Logger logger = LogManager.getLogger();
    @Spy
    private TextReaderFromFile readerMock;
    private ParagraphParser parser;
    private TextComponent result;
    private String text;
    private String expected;
    private int listLength;
    private int expectedListLength;

    @BeforeAll
    void setUp() throws HandingException {
        logger.info("The testing is starting...");
        MockitoAnnotations.openMocks(this);
        parser = new ParagraphParser();
        text = "I like to eat. You like to eat. They like to eat.";
        Mockito.doReturn(text)
                .when(readerMock)
                .readText(Mockito.any());
        result = parser.parse(readerMock.readText(Mockito.any()));
        listLength = result.getComponents().size();
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished...");
    }

    @Test
    public void parse() {
        expected = text;
        assertEquals(expected, result.toString().trim());
    }

    @Test
    public void checkListLength() {
        expectedListLength = 3;
        assertEquals(expectedListLength, listLength);
    }
}