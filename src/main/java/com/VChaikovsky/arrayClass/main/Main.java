package com.VChaikovsky.arrayClass.main;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.service.impl.DataProcessing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Main {
    final static Logger logger = LogManager.getLogger();
    static CustomArray customArray = new CustomArray(new Double[]{-5.4, .78, 3.5, 4.0, 103.05, 0.0, 23.9});
    static DataProcessing dataProcessing = new DataProcessing();

    public static void main(String[] args) throws WrongDataException {


        logger.info("The result of array processing:\n" +
                "min meaning is "+dataProcessing.findMin(customArray)+",\n" +
                "max meaning is "+dataProcessing.findMax(customArray)+",\n" +
                "average meaning is "+dataProcessing.findAverage(customArray)+",\n" +
                "amount of numbers is "+dataProcessing.findNumbersAmount(customArray)+",\n" +
                "the quantity of negative numbers is "+dataProcessing.findNegativeQuantity(customArray)+",\n" +
                "the quantity of positive numbers is "+dataProcessing.findPositiveQuantity(customArray)+",\n" +
                "the array after replacing negative numbers has view: "+ Arrays.toString(dataProcessing.changeAllNegativeNumbers(customArray).getArray()));



    }

}