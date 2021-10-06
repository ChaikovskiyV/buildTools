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
        Integer number = processing.findMax(customArray);
        assertEquals(expectedResult, number);
    }

    @Test
    public void findAverage() throws WrongDataException {
        double expectedResult = 0.0;
        for(Integer number : sourceArray){
            expectedResult += number;
        }
        expectedResult /= sourceArray.length;

        double average = processing.findAverage(customArray);

        assertEquals(expectedResult, average);
    }

    @Test
    public void findNumbersAmount() throws WrongDataException {
        expectedResult = 0;
        for(Integer number : sourceArray){
            expectedResult += number;
        }
        int amount = processing.findNumbersAmount(customArray);

        assertEquals(expectedResult, amount);
    }

    @Test
    public void findNegativeQuantity() {
        long expectedResult = 3;
        long result = processing.findNegativeQuantity(customArray);
        assertEquals(expectedResult, result);
    }

    @Test
    public void findPositiveQuantity() {
        long expectedResult = 4;
        long result = processing.findPositiveQuantity(customArray);
        assertEquals(expectedResult, result);
    }

    @Test
    public void replaceAllNegativeAndNullNumbersToZero() {
        Integer[] array = processing
                .replaceAllNegativeNumbersToZero(customArray)
                .getArray();
        assertArrayEquals(expectedArray, array);
    }
}