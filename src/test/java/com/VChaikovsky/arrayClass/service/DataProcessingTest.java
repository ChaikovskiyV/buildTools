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
    Integer[] sourceArray;
    Integer expectedResult;
    Integer[] expectedArray;
    Integer[] emptyArray;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        processing = new DataProcessing();
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
    public void ifArrayIncludesNull(){
        Integer[] someArray = new Integer[]{1, 3, null, 5};
        assertThrows(WrongDataException.class, ()->processing.findMin(new CustomArray(someArray)));
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
    public void findNumbersAmount() {
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
    public void changeAllNegativeNumbers() {
        assertArrayEquals(expectedArray, processing.replaceAllNegativeAndNullNumbersToZero(customArray).getArray());
    }
}