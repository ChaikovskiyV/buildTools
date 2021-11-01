package com.VChaikovsky.arrayClass.convector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArrayConvectorIntTest {
    static final Logger logger = LogManager.getLogger();
    private Integer[] sourceArray;
    private int[] intArray;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        sourceArray = new Integer[] {3, 6, 9, 12};
        intArray = new int[] {1, 10, 12, 45};
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void convectToInteger() {
        Integer[] expectedArray = {3, 6, 9, 12};
        assertArrayEquals(expectedArray, sourceArray);
    }

    @Test
    public void convectToInt() {
        int[] expectedArray = {1, 10, 12, 45};
        assertArrayEquals(expectedArray, intArray);
    }
}