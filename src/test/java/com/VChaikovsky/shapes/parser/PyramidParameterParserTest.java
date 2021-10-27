package com.vchaikovsky.shapes.parser;

import com.vchaikovsky.shapes.exception.ShapeException;
import com.vchaikovsky.shapes.parser.impl.PyramidParameterParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PyramidParameterParserTest {
    static final Logger logger = LogManager.getLogger();
    private PyramidParameterParser parser;
    private String sourceFile;
    private String wrongDataFile;
    private List<String> data;
    private List<String> wrongData;
    private List<double[]> result;
    private List<double[]> expendedResult;

    @org.junit.jupiter.api.BeforeAll
    void setUp() throws ShapeException {
        logger.info("Testing is starting ...");
        parser = PyramidParameterParser.getInstance();
        sourceFile = "sources/testparserdata.txt";
        wrongDataFile = "sources/wrongdataparser.txt";
        Path path = null;
        try {
            path = Path.of(sourceFile);
            data = Files.readAllLines(path);
            path = Path.of(wrongDataFile);
            wrongData = Files.readAllLines(path);
        } catch (IOException e) {
            logger.error("The file " + path + "was not found.");
            throw new ShapeException("The file " + path + "was not found.", e);
        }
        expendedResult = Stream.of(new double[]{16, 58, 96, 16, 34, 96, 4, 15}, new double[]{20, 30, 50, 0, 30, 50, 5, 50})
                        .toList();
    }

    @org.junit.jupiter.api.AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @org.junit.jupiter.api.Test
    public void parseStrToPyramidParam() throws ShapeException {
        result = parser.parseStrToPyramidParam(data);

        assertArrayEquals(expendedResult.toArray(), result.toArray());
    }

    @Test
    public void ifSourceDataIsWrong() throws ShapeException {
        result = parser.parseStrToPyramidParam(wrongData);
        expendedResult = new ArrayList<>();

        assertEquals(expendedResult, result);
    }

    @Test
    public void ifSourceDataIsEmpty() {
        data = new ArrayList<>();
        assertThrows(ShapeException.class, ()->parser.parseStrToPyramidParam(data));
    }
}