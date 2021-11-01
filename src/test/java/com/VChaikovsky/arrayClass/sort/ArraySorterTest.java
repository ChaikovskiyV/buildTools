package com.VChaikovsky.arrayClass.sort;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.sort.impl.ArraySorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArraySorterTest {
    static final Logger logger = LogManager.getLogger();
    private ArraySorter sorter;
    private Integer[] sourceArray;
    private Integer[] expectedArray;

    @BeforeAll
    void setUp() {
        expectedArray = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sorter = new ArraySorter();
    }

    @BeforeEach
    void tearDown() {
        sourceArray = new Integer[] {9, 6, 4, 8, 2, 5, 1, 7, 3};
    }

    @Test
    public void choiceSort() throws WrongDataException {
        sorter.choiceSort(sourceArray);
        assertArrayEquals(expectedArray, sourceArray);
    }

    @Test
    public void insertSort() throws WrongDataException {
        sorter.insertSort(sourceArray);
        assertArrayEquals(expectedArray, sourceArray);
    }

    @Test
    public void bubbleSort() throws WrongDataException {
        sorter.insertSort(sourceArray);
        assertArrayEquals(expectedArray, sourceArray);
    }

    @Test
    public void streamSort() throws WrongDataException {
        Integer[] array = sorter.streamSort(sourceArray);

        assertArrayEquals(expectedArray, array);
    }
}