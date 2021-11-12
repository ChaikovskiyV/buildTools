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

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordParserTest {
    static final Logger logger = LogManager.getLogger();
    @Spy
    private TextReaderFromFile reader;
    private WordParser parser;
    private String textOne;
    private String textTwo;
    private String textThree;
    private String expected;
    private TextComponent result;
    private int listLength;
    private int expectedLength;

    @BeforeAll
    void setUp() throws HandingException {
        logger.info("The testing is starting...");
        MockitoAnnotations.openMocks(this);
        parser = new WordParser();
        textOne = "I'm";
        textTwo = "more-or-less";
        textThree = "happy";
        Mockito.doReturn(textOne, textTwo, textThree)
                .when(reader)
                .readText(Mockito.any());
    }

    @BeforeEach
    void setMock() throws HandingException {
        result = parser.parse(reader.readText(Mockito.any()));
        listLength = result.getComponents().size();
    }

    @AfterEach
    @Test
    public void checkListLength() {
        assertEquals(expectedLength, listLength);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void parseIfHyphanInside() {
        expectedLength = 12;
        expected = " more-or-less";
        assertEquals(expected, result.toString());
    }

    @Test
    public void parseIfApodtraphInside() {
        expectedLength = 3;
        expected = " I'm";
        assertEquals(expected, result.toString());
    }

    @Test
    public void parse() {
        expectedLength = 5;
        expected = " happy";
        assertEquals(expected, result.toString());
    }
}