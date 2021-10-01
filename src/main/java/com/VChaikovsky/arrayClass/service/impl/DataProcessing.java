package com.VChaikovsky.arrayClass.service.impl;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.service.DataProcessingInt;
import com.VChaikovsky.arrayClass.validation.impl.ArrayValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataProcessing implements DataProcessingInt {
    final static Logger logger = LogManager.getLogger();
    public ArrayValidation validation = new ArrayValidation();

    @Override
    public Double findMin(CustomArray array) throws WrongDataException {
        List<Double> numbersList = null;
        if(validation.validateArray(array.getArray())){
            numbersList = Arrays.asList(array.getArray());
        }
        return Collections.min(numbersList);
    }

    @Override
    public Double findMax(CustomArray array) throws WrongDataException {
        List<Double> numbersList = null;
        if(validation.validateArray(array.getArray())){
            numbersList = Arrays.asList(array.getArray());
        }
        return Collections.max(numbersList);
    }

    @Override
    public Double findAverage(CustomArray array) throws WrongDataException {

        return findNumbersAmount(array) / array.getArray().length;
    }

    @Override
    public Double findNumbersAmount(CustomArray array) throws WrongDataException {
        Double average = null;
        if(validation.validateArray(array.getArray())){
            for(Double number : array.getArray()) {
                if (average == null) {
                    average = number;
                } else {
                    average += number;
                }
            }
        }
        return average;
    }

    @Override
    public int findNegativeQuantity(CustomArray array) throws WrongDataException {
        int countNegativeNumbers = 0;
        if(validation.validateArray(array.getArray())){
            for (Double number: array.getArray()){
                if(number < 0){
                    countNegativeNumbers++;
                }
            }
        }
        return countNegativeNumbers;
    }

    @Override
    public int findPositiveQuantity(CustomArray array) throws WrongDataException {
        int countPositiveNumbers = 0;
        if(validation.validateArray(array.getArray())){
            for (Double number: array.getArray()){
                if(number > 0){
                    countPositiveNumbers++;
                }
            }
        }
        return countPositiveNumbers;
    }

    @Override
    public CustomArray changeAllNegativeNumbers(CustomArray array) throws WrongDataException {
        Double[] newArray = null;
        if(validation.validateArray(array.getArray())){
            newArray = array.getArray();
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i] < 0){
                    newArray[i] *= -1;
                }
            }
        }
        return new CustomArray(newArray);
    }
}