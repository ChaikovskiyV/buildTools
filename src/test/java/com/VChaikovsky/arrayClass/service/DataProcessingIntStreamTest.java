package com.VChaikovsky.arrayClass.service;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.service.impl.DataProcessingIntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataProcessingIntStreamTest {
final static Logger logger = LogManager.getLogger();

    DataProcessingIntStream processing;
    CustomArray customArray;
    Integer[] sourceArray;
    Integer expectedResult;
    Integer[] expectedArray;
    Integer[] emptyArray;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        processing = new DataProcessingIntStream();
        sourceArray = new Integer[] {3, 50, -96, 45, 19, -20, -73};
        customArray = new CustomArray(sourceArray);
        expectedArray = new Integer[] {3, 50, 0, 45, 19, 0, 0};
        emptyArray = null;
    }

    @AfterAll
    void tearDown() {
        logger.info("Tests have been finished.");
    }

    @Test
    public void findMin() throws WrongDataException {
        expectedResult = -96;
        assertEquals(expectedResult, processing.findMin(customArray));
    }

    @Test
    public void findMax() throws WrongDataException {
        expectedResult = 50;
        assertEquals(expectedResult, processing.findMax(customArray));
    }

    @Test
    public void findAverage() throws WrongDataException {
        double expectedResult = 0.0;
        for(Integer number : sourceArray){
            expectedResult += number;
        }
        expectedResult /= sourceArray.length;

        assertEquals(expectedResult, processing.findAverage(customArray));
    }

    @Test
    public void findNumbersAmount() throws WrongDataException {
        expectedResult = 0;
        for(Integer number : sourceArray){
            expectedResult += number;
        }
        assertEquals(expectedResult, processing.findNumbersAmount(customArray));
    }

    @Test
    public void findNegativeQuantity() {
        int expectedResult = 3;
        assertEquals(expectedResult, processing.findNegativeQuantity(customArray));
    }

    @Test
    public void findPositiveQuantity() {
        int expectedResult = 4;
        assertEquals(expectedResult, processing.findPositiveQuantity(customArray));
    }

    @Test
    public void replaceAllNegativeAndNullNumbersToZero() {
        assertArrayEquals(expectedArray, processing.replaceAllNegativeNumbersToZero(customArray).getArray());
    }
}