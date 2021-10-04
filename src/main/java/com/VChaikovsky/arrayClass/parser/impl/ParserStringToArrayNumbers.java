package com.VChaikovsky.arrayClass.parser.impl;

import com.VChaikovsky.arrayClass.parser.ParserStringToArrayNumbersInt;

import java.util.ArrayList;
import java.util.List;

public class ParserStringToArrayNumbers implements ParserStringToArrayNumbersInt {
    private final String REGEX = "(\\s-\\s)|[\\W_:&&[^-]]+";

    @Override
    public Integer[] parseStringToIntArray(String str) {
        List<Integer> listNumbers = new ArrayList<>();
        String[] strings = str.split(REGEX);
        for(String string : strings){
            Integer number = Integer.parseInt(string);
            listNumbers.add(number);
        }
        return listNumbers.toArray(Integer[]::new);
    }
}
