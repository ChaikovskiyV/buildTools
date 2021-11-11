package com.vchaikovsky.informationhanding.reader;

import com.vchaikovsky.informationhanding.exception.HandingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TextRiderFromFileTest {
    static final Logger logger = LogManager.getLogger();
    private TextReaderFromFile reader;
    private String filename;
    private String wrongFilename;
    private String extended;
    private String result;

    @BeforeAll
    void setUp() {
        logger.info("The testing is starting...");
        reader = TextReaderFromFile.getInstance();
        filename = "sources/testtext.txt";
        wrongFilename = "testtext.txt";
        extended = "\s\sI like to eat. You like to eat. They like to eat.\n\s\sTacos, tacos. Burrito, burrito.\n\s\sEverybody wanna eat salts and sweets…\n";
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void readText() throws HandingException {
        result = reader.readText(filename);

        assertEquals(extended, result);
    }

    @Test
    public void whetherThrowExceptionIfFileNotFound() {
        assertThrows(HandingException.class, () -> reader.readText(wrongFilename));
    }
}