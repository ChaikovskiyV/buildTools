package com.vchaikovsky.arrayclass.reader;

import com.vchaikovsky.arrayclass.exceptions.WrongDataException;
import com.vchaikovsky.arrayclass.reader.impl.ReadFromFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReadFromFileTest {
    static final Logger logger = LogManager.getLogger();
    private ReadFromFile reader;
    private String filename;
    private String wrongDataFile;
    private String expectedResult;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        reader = new ReadFromFile();
        filename = "sourcedata.txt";
        wrongDataFile = "wrongdata.txt";
        expectedResult = "52 : 61 : 74 : 18";
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void readString() throws WrongDataException {
        String str = reader.readString(filename);
        assertEquals(expectedResult, str);
    }

    @Test
    public void ifNoCorrectStrings(){
        assertThrows(WrongDataException.class, ()->reader.readString(wrongDataFile));
    }}