package com.VChaikovsky.arrayclass.validation.impl;

import com.VChaikovsky.arrayclass.validation.DataValidationInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataValidation implements DataValidationInt {
    static final Logger logger = LogManager.getLogger();

    public boolean validateArray(Integer[] array){
        boolean correctArray = true;
        int count = 0;
        while (count < array.length){
            if(array[count] == null){
                correctArray = false;
                break;
            } else {
                count++;
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
            int count = 0;
            while (count < chars.length){
                if(Character.isAlphabetic(chars[count])){
                    correctData = false;
                    break;
                } else {
                    count++;
                }
            }
        }
        return correctData;
    }

    @Override
    public boolean isIncludesNumbers(String str) {
        boolean stringIncludeNumbers = false;
        char[] chars = str.toCharArray();
        int count = 0;
        while (count < chars.length){
            if (Character.isDigit(chars[count])){
                stringIncludeNumbers = true;
                break;
            } else {
                count++;
            }
        }
        return stringIncludeNumbers;
    }
}