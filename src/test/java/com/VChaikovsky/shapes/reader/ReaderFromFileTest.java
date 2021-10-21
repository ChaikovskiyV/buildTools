package com.VChaikovsky.shapes.reader;

import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.reader.impl.ReaderFromFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReaderFromFileTest {
    static final Logger logger = LogManager.getLogger();
    private ReaderFromFile reader;
    private String filename;
    private String nonexistentFile;
    private String wrongDataFile;
    private List<String> result;
    private List<String> expectedResult;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        reader = ReaderFromFile.getInstance();
        filename = "sources/testreaderdata.txt";
        wrongDataFile = "sources/wrongdatareader.txt";
        nonexistentFile = "nonexistent.txt";
        expectedResult = Stream.of("-3 8 12 -3 -2 12 3 6", "2 11 6 14 11 6 4 10", "10 15 20 10 15 20 4 35")
                .toList();
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void readData() throws ShapeException {
        result = reader.readData(filename);

        assertEquals(expectedResult, result);
    }

    @Test
    public void ifFileNotExists(){
        assertThrows(ShapeException.class, ()->reader.readData(nonexistentFile));
    }

    @Test
    public void ifDataIsWrong() throws ShapeException {
        result = reader.readData(wrongDataFile);
        expectedResult = new ArrayList<>();

        assertEquals(expectedResult, result);
    }
}