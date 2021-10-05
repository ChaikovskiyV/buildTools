package com.VChaikovsky.arrayClass.sort.impl;

import com.VChaikovsky.arrayClass.convector.ArrayConvectorInt;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.sort.ArraySorterInt;
import com.VChaikovsky.arrayClass.validation.impl.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

public class ArraySorter implements ArraySorterInt, ArrayConvectorInt {
    final static Logger logger = LogManager.getLogger();
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
            throwException();
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
            throwException();
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
            throwException();
        }
    }

    @Override
    public Integer[] streamSort(Integer[] array) throws WrongDataException {
        int[] newArray = new int[array.length];
        if(validation.validateArray(array)){
           newArray =  IntStream.of(convectToInt(array))
                    .sorted()
                    .toArray();
        } else {
            throwException();
        }
        return convectToInteger(newArray);
    }

    private void throwException() throws WrongDataException {
        try {
            throw new WrongDataException();
        } catch (NullPointerException e){
            logger.throwing(Level.ERROR, new WrongDataException("The array contains null element", e));
        }
    }
}