package com.VChaikovsky.arrayClass.validation.impl;

import com.VChaikovsky.arrayClass.validation.DataValidationInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataValidation implements DataValidationInt {
    final static Logger logger = LogManager.getLogger();

    public boolean validateArray(Integer[] array){
        boolean correctArray = true;
        for(Integer number : array){
            if(number == null){
                correctArray = false;
                break;
            }
        }
        return correctArray;
    }

    @Override
    public boolean isCorrectData(String str) {
        boolean correctData = true;
        if(str.isEmpty()){
            correctData = false;
        } else {
            char[] chars = str.toCharArray();
            for(char symbol : chars){
                if(Character.isAlphabetic(symbol)){
                    correctData = false;
                    break;
                }
            }
        }
        return correctData;
    }

    @Override
    public boolean isIncludesNumbers(String str) {
        boolean stringIncludeNumbers = false;
        for(char symbol : str.toCharArray()){
            if (Character.isDigit(symbol)){
                stringIncludeNumbers = true;
                break;
            }
        }
        return stringIncludeNumbers;
    }
}