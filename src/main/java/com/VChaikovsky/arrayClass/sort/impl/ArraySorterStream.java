package com.VChaikovsky.arrayClass.sort.impl;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.sort.ArraySorterStreamInt;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArraySorterStream implements ArraySorterStreamInt {

    @Override
    public void sortArray(CustomArray array) {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);
        stream.sorted();
        int [] newArray = stream.toArray();
        array.setArray(convertIntArrayToInteger(newArray));

    }

    public Integer[] convertIntArrayToInteger(int[] array){
        Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = Integer.valueOf(array[i]);
        }
        return newArray;
    }
}
