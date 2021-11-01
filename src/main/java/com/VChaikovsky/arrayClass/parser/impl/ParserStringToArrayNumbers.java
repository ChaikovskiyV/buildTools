package com.VChaikovsky.arrayClass.parser.impl;

import com.VChaikovsky.arrayClass.parser.ParserStringToArrayNumbersInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ParserStringToArrayNumbers implements ParserStringToArrayNumbersInt {
    static final Logger logger = LogManager.getLogger();
    static final String STRING_DELIMITER = "(\\s-\\s)|[\\W_:&&[^-]]+";

    @Override
    public Integer[] parseStringToIntArray(String str) {
        List<Integer> listNumbers = new ArrayList<>();
        String[] strings = str.split(STRING_DELIMITER);
        for(String string : strings){
            Integer number = Integer.parseInt(string);
            listNumbers.add(number);
        }
        return listNumbers.toArray(Integer[]::new);
    }
}