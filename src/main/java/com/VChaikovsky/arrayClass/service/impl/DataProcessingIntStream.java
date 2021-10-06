package com.VChaikovsky.arrayClass.service.impl;

import com.VChaikovsky.arrayClass.convector.ArrayConvectorInt;
import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.service.DataProcessingStreamInt;
import com.VChaikovsky.arrayClass.validation.impl.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

public class DataProcessingIntStream implements DataProcessingStreamInt, ArrayConvectorInt {
    final static Logger logger = LogManager.getLogger();
    private DataValidation validation = new DataValidation();

    @Override
    public Integer findMin(CustomArray array) throws WrongDataException {
        int min = 0;
        if(validation.validateArray(array.getArray())){
            min = IntStream
                    .of(convectToInt(array.getArray()))
                    .min()
                    .getAsInt();
        } else {
            throwException();
        }
        return min;
    }

    @Override
    public Integer findMax(CustomArray array) throws WrongDataException {
        int max = 0;
        if (validation.validateArray(array.getArray())) {
            max = IntStream
                    .of(convectToInt(array.getArray()))
                    .max()
                    .getAsInt();
        } else {
            throwException();
        }
        return max;
    }

    @Override
    public double findAverage(CustomArray array) throws WrongDataException {
        double average = 0.0;
        if(validation.validateArray(array.getArray())){
            average = IntStream
                    .of(convectToInt(array.getArray()))
                    .average()
                    .getAsDouble();

        } else {
            throwException();
        }
        return average;
    }

    @Override
    public int findNumbersAmount(CustomArray array) throws WrongDataException {
        int amount = 0;
        if(validation.validateArray(array.getArray())){
            amount = IntStream
                    .of(convectToInt(array.getArray()))
                    .sum();

        } else {
            throwException();
        }
        return amount;
    }

    @Override
    public long findNegativeQuantity(CustomArray array) {
        long negativeNumbers = IntStream
                .of(convectToInt(array.getArray()))
                .filter(number->(number < 0))
                .count();

        return  negativeNumbers;
    }

    @Override
    public long findPositiveQuantity(CustomArray array) {
        long positiveNumbers = IntStream
                .of(convectToInt(array.getArray()))
                .filter(number->(number > 0))
                .count();

        return positiveNumbers;
    }

    @Override
    public CustomArray replaceAllNegativeNumbersToZero(CustomArray array) {
        int[] newArray = IntStream
                .of(convectToInt(array.getArray()))
                .map(number -> {
                    if(number < 0){
                        number = 0;
                    }
                    return number;
                })
                .toArray();
        CustomArray customArray = new CustomArray(convectToInteger(newArray));

        return  customArray;
    }

    private void throwException() throws WrongDataException {
        try {
            throw new WrongDataException();
        } catch (NullPointerException e){
            logger.throwing(Level.ERROR, new WrongDataException("The array contains null element", e));
        }
    }
}