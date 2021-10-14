package com.VChaikovsky.shapes.parser;

import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.parser.impl.PyramidParameterParser;
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
    private PyramidParameterParser parser;
    private Logger logger = LogManager.getLogger();
    private String sourceFile;
    private String wrongDataFile;
    private List<String> data;
    private List<String> wrongData;
    private List<double[]> result;
    private List<double[]> expendedResult;

    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        parser = new PyramidParameterParser();
        sourceFile = "sources/testparserdata.txt";
        wrongDataFile = "sources/wrongdataparser.txt";
        Path path;
        try {
            path = Path.of(sourceFile);
            data = Files.readAllLines(path);
            path = Path.of(wrongDataFile);
            wrongData = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        expendedResult = Stream
                .of(new double[]{16, 58, 96, 16, 34, 96, 4, 15}, new double[]{20, 30, 50, 0, 30, 50, 5, 50})
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