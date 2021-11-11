package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.exception.HandingException;
import com.vchaikovsky.informationhanding.reader.TextReaderFromFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(OrderAnnotation.class)
public class LexemeParserTest {
    static final Logger logger = LogManager.getLogger();

    private TextReaderFromFile reader;
    private String textOne;
    private String textTwo;
    private String textThree;
    private String expected;
    private TextComponent result;
    private int listLength;
    private int expectedLength;
    private LexemeParser parser;

    @BeforeAll
    void setUp() throws HandingException {
        logger.info("The testing is starting...");
        parser = new LexemeParser();
        textOne = "Hello!";
        textTwo = "\"hen\"";
        textThree = "(hare";
        reader = Mockito.spy(TextReaderFromFile.getInstance());
        Mockito
                .doReturn(textOne, textTwo, textThree)
                .when(reader)
                .readText(Mockito.any());

    }

    @BeforeEach
    void setMock() throws HandingException {
        result = parser
                .parse(reader.readText(Mockito.any()));
        listLength = result
                .getComponents()
                .size();
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

    //@Order(1)
    @Test
    public void parseIfLastNotLetter() {
        expectedLength = 2;
        expected = " Hello!";
        assertEquals(expected, result.toString());
    }

    /*@Order(2)
    @Test
    public void equalsListsLengthIfLastNotLetter() {
        expectedLength = 2;

        assertEquals(expectedLength, listLength);
    }*/

    //@Order(3)
    @Test
    public void parseIfFirstNotLetter() {
        expectedLength = 2;
        expected = "( hare";
        assertEquals(expected, result.toString());
    }

   /* @Order(4)
    @Test
    public void equalsListsLengthIfFirstNotLetter() {
        expectedLength = 2;

        assertEquals(expectedLength, listLength);
    }*/

    //@Order(3)
    @Test
    public void parseIfFirstAndLastNotLetters() {
        expectedLength = 3;
        expected = "\" hen\"";
        assertEquals(expected, result.toString());
    }

    /*@Order(6)
    @Test
    public void equalsListsLengthIfFirstAndLastNotLetters() {
        expectedLength = 3;

        assertEquals(expectedLength, listLength);
    }*/
}