package com.vchaikovsky.arrayclass.service;

import com.vchaikovsky.arrayclass.creater.EntityCreater;
import com.vchaikovsky.arrayclass.entity.CustomArray;
import com.vchaikovsky.arrayclass.exceptions.WrongDataException;
import com.vchaikovsky.arrayclass.service.impl.DataProcessing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataProcessingTest {
    static final Logger logger = LogManager.getLogger();
    private DataProcessing processing;
    private CustomArray customArray;
    private Integer[] sourceArray;
    private Integer expectedResult;
    private Integer[] expectedArray;
    private Integer[] emptyArray;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        processing = new DataProcessing();
        sourceArray = new Integer[] {3, 50, -96, 45, 19, -20, -73};
        customArray = EntityCreater.createEntity(sourceArray);
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
        Integer result = processing.findMin(customArray);

        assertEquals(expectedResult, result);
    }

    @Test
    public void ifArrayIncludesNull(){
        Integer[] someArray = new Integer[]{1, 3, null, 5};

        assertThrows(WrongDataException.class, ()->processing
                .findMin(EntityCreater.createEntity(someArray)));
    }

    @Test
    public void findMax() throws WrongDataException {
        expectedResult = 50;
        Integer max = processing.findMax(customArray);

        assertEquals(expectedResult, max);
    }

    @Test
    public void findAverage() {
        double expectedResult = 0.0;
        for(Integer number : sourceArray){
                expectedResult += number;
        }
        expectedResult /= sourceArray.length;
        double average = processing.findAverage(customArray);

        assertEquals(expectedResult, average);
    }

    @Test
    public void findNumbersAmount() {
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
    public void changeAllNegativeNumbers() {
        Integer[] array = processing
                .replaceAllNegativeNumbersToZero(customArray)
                .getArray();
        assertArrayEquals(expectedArray, array);
    }
}