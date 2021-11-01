package com.VChaikovsky.arrayClass.service.impl;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.service.DataProcessingInt;
import com.VChaikovsky.arrayClass.validation.impl.DataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataProcessing implements DataProcessingInt {
    static final Logger logger = LogManager.getLogger();
    private DataValidation validation = new DataValidation();

    @Override
    public Integer findMin(CustomArray array) throws WrongDataException {
        List<Integer> listNumbers;
        Integer[] numbers = array.getArray();

        if(validation.validateArray(numbers)){
            listNumbers = Arrays.asList(numbers);
        } else {
            logger.error("The array contains null element.");
            throw new WrongDataException("The array contains null element.");
        }
        return Collections.min(listNumbers);
    }

    @Override
    public Integer findMax(CustomArray array) throws WrongDataException {
        List<Integer> numbersList;
        Integer[] numbers = array.getArray();

        if(validation.validateArray(numbers)){
            numbersList = Arrays.asList(numbers);
        } else {
            logger.error("The array contains null element.");
            throw new WrongDataException("The array contains null element.");
        }
        return Collections.max(numbersList);
    }

    @Override
    public int findNumbersAmount(CustomArray array){
        int amount = 0;
        Integer[] numbers = array.getArray();
        for(Integer number : numbers) {
            if(number != null){
                amount += number;
            }
        }
        return amount;
    }

    @Override
    public double findAverage(CustomArray array){
        double convect = 1.0;
        Integer[] numbers = array.getArray();
        return findNumbersAmount(array) * convect / numbers.length;
    }

    @Override
    public int findNegativeQuantity(CustomArray array) {
        int countNegativeNumbers = 0;
        Integer[] numbers = array.getArray();
            for (Integer number: numbers){
                if(number < 0){
                    countNegativeNumbers++;
            }
        }
        return countNegativeNumbers;
    }

    @Override
    public int findPositiveQuantity(CustomArray array) {
        int countPositiveNumbers = 0;
        Integer[] numbers = array.getArray();
            for (Integer number: numbers){
                if(number > 0){
                    countPositiveNumbers++;
            }
        }
        return countPositiveNumbers;
    }

    @Override
    public CustomArray replaceAllNegativeNumbersToZero(CustomArray array) {
        Integer[] newArray = array.getArray();
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i] < 0) {
                    newArray[i] = 0;
                }
            }
        return new CustomArray(newArray);
    }
}