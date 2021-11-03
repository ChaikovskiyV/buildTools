package com.VChaikovsky.arrayclass.sort.impl;

import com.VChaikovsky.arrayclass.convector.ArrayConvectorInt;
import com.VChaikovsky.arrayclass.exceptions.WrongDataException;
import com.VChaikovsky.arrayclass.sort.ArraySorterInt;
import com.VChaikovsky.arrayclass.validation.impl.DataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

public class ArraySorter implements ArraySorterInt, ArrayConvectorInt {
    static final Logger logger = LogManager.getLogger();
    private DataValidation validation = new DataValidation();

    @Override
    public void choiceSort(Integer[] array) throws WrongDataException {
        if(validation.validateArray(array)) {
            for (int i = 0; i < array.length; i++) {
                int min = array[i];
                int minId = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < min) {
                        min = array[j];
                        minId = j;
                    }
                }
                int number = array[i];
                array[i] = min;
                array[minId] = number;
            }
        } else {
            logger.error("The array contains null element.");
            throw new WrongDataException("The array contains null element.");
        }
    }

    @Override
    public void insertSort(Integer[] array) throws WrongDataException {
        if (validation.validateArray(array)) {
            for (int i = 1; i < array.length; i++) {
                int number = array[i];
                int j = i - 1;
                while (j >= 0 && number < array[j]) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = number;
            }
        } else {
            logger.error("The array contains null element.");
            throw new WrongDataException("The array contains null element.");
        }
    }

    @Override
    public void bubbleSort(Integer[] array) throws WrongDataException {
        if (validation.validateArray(array)) {
            for (int i = 0; i < array.length - 1; i++) {
                int min = array[i];
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < min) {
                        min = array[j];
                        array[j] = array[i];
                        array[i] = min;
                    }
                }
            }
        } else {
            logger.error("The array contains null element.");
            throw new WrongDataException("The array contains null element.");
        }
    }

    @Override
    public Integer[] streamSort(Integer[] array) throws WrongDataException {
        int[] newArray;
        if(validation.validateArray(array)){
           newArray =  IntStream.of(convectToInt(array))
                    .sorted()
                    .toArray();
        } else {
            logger.error("The array contains null element.");
            throw new WrongDataException("The array contains null element.");
        }
        return convectToInteger(newArray);
    }
}