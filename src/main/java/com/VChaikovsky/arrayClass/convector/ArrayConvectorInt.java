package com.VChaikovsky.arrayClass.convector;

public interface ArrayConvectorInt {
    default Integer[] convectToInteger(int [] array) {
        Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = Integer.valueOf(array[i]);
        }
        return newArray;
    }

    default int[] convectToInt(Integer[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}