package com.vchaikovsky.arrayclass.main;

import com.vchaikovsky.arrayclass.creater.EntityCreater;
import com.vchaikovsky.arrayclass.entity.CustomArray;
import com.vchaikovsky.arrayclass.exceptions.WrongDataException;
import com.vchaikovsky.arrayclass.parser.ParserStringToArrayNumbersInt;
import com.vchaikovsky.arrayclass.parser.impl.ParserStringToArrayNumbers;
import com.vchaikovsky.arrayclass.reader.impl.ReadFromFile;
import com.vchaikovsky.arrayclass.service.impl.DataProcessing;
import com.vchaikovsky.arrayclass.sort.impl.ArraySorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Main {
    static final Logger logger = LogManager.getLogger();
    static CustomArray customArray;
    static DataProcessing dataProcessing = new DataProcessing();
    static ReadFromFile reader = new ReadFromFile();
    static ArraySorter sorter = new ArraySorter();
    static String filename = "sourcedata.txt";
    static ParserStringToArrayNumbersInt parser = new ParserStringToArrayNumbers();

    public static void main(String[] args) throws WrongDataException {
        String string = reader.readString(filename);
        Integer[] numbers = parser.parseStringToIntArray(string);
        customArray = EntityCreater.createEntity(numbers);

        logger.info("The result of array processing:\n" +
                "min meaning is "+dataProcessing.findMin(customArray)+",\n" +
                "max meaning is "+dataProcessing.findMax(customArray)+",\n" +
                "average meaning is "+dataProcessing.findAverage(customArray)+",\n" +
                "amount of numbers is "+dataProcessing.findNumbersAmount(customArray)+",\n" +
                "the quantity of negative numbers is "+dataProcessing.findNegativeQuantity(customArray)+",\n" +
                "the quantity of positive numbers is "+dataProcessing.findPositiveQuantity(customArray)+",\n" +
                "the array after replacing negative numbers has a view: "+ Arrays.toString(dataProcessing
                .replaceAllNegativeNumbersToZero(customArray)
                .getArray()) + ",\n" +
                "the sorted array has a view: " + Arrays
                .toString(sorter
                .streamSort(customArray
                        .getArray())));
    }
}