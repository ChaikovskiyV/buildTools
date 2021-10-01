package com.VChaikovsky.arrayClass.service;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.service.impl.DataProcessing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataProcessingTest {
    final static Logger logger = LogManager.getLogger();
    DataProcessing processing;
    CustomArray customArray;
    Double[] sourceArray;
    Double expectedResult;
    Double[] expectedArray;
    Double[] emptyArray;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        processing = new DataProcessing();
        sourceArray = new Double[] {3.0, 5.0, -9.6, .45, 1.9, -2.0, -7.3};
        customArray = new CustomArray(sourceArray);
        expectedArray = new Double[] {3.0, 5.0, 9.6, .45, 1.9, 2.0, 7.3};
        emptyArray = null;
    }

    @AfterAll
    void tearDown() {
        logger.info("Tests have been finished.");
    }

    @Test
    public void findMin() throws WrongDataException {
        expectedResult = -9.6;
        assertEquals(expectedResult, processing.findMin(customArray));
    }

    @Test
    public void findMax() throws WrongDataException {
        expectedResult = 5.0;
        assertEquals(expectedResult, processing.findMax(customArray));
    }

    @Test
    public void findAverage() throws WrongDataException {
        expectedResult = .0;
        for(Double number : sourceArray){
            if(number != null){
                expectedResult += number;
            }
        }
        expectedResult /= sourceArray.length;

        assertEquals(expectedResult, processing.findAverage(customArray));
    }

    @Test
    public void findNumbersAmount() throws WrongDataException {
        expectedResult = .0;
        for(Double number : sourceArray){
            if(number != null){
                expectedResult += number;
            }
        }

        assertEquals(expectedResult, processing.findNumbersAmount(customArray));
    }

    @Test
    public void findNegativeQuantity() throws WrongDataException {
        int expectedResult = 3;
        assertEquals(expectedResult, processing.findNegativeQuantity(customArray));
    }

    @Test
    public void findPositiveQuantity() throws WrongDataException {
        int expectedResult = 4;
        assertEquals(expectedResult, processing.findPositiveQuantity(customArray));
    }

    @Test
    public void changeAllNegativeNumbers() throws WrongDataException {
        assertArrayEquals(expectedArray, processing.changeAllNegativeNumbers(customArray).getArray());
    }
}