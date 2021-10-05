package com.VChaikovsky.arrayClass.reader;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.reader.impl.ReadFromFile;
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
final static Logger logger = LogManager.getLogger();
ReadFromFile reader;
private String filename;
private String wrongDataFile;
String expectedResult;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        reader = new ReadFromFile();
        filename = "sources/sourcedata.txt";
        wrongDataFile = "sources/wrongdata.txt";
        expectedResult = "52 : 61 : 74 : 18";
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void readString() throws WrongDataException {
        assertEquals(expectedResult, reader.readString(filename));
    }

    @Test
    public void ifNoCorrectStrings(){
        assertThrows(WrongDataException.class, ()->reader.readString(wrongDataFile));
    }}