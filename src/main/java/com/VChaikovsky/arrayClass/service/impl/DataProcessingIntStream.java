package com.VChaikovsky.arrayClass.service.impl;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.service.DataProcessingStreamInt;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DataProcessingIntStream implements DataProcessingStreamInt {


    @Override
    public Integer findMin(CustomArray array) throws WrongDataException {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);

        return stream.min().getAsInt();
    }

    @Override
    public Integer findMax(CustomArray array) throws WrongDataException {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);

        return stream.max().getAsInt();
    }

    @Override
    public double findAverage(CustomArray array) throws WrongDataException {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);

        return stream.average().getAsDouble();
    }

    @Override
    public int findNumbersAmount(CustomArray array) throws WrongDataException {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);

        return stream.sum();
    }

    @Override
    public long findNegativeQuantity(CustomArray array) throws WrongDataException {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);
        long negativeNumbers = stream.filter(x->x < 0)
                .findAny()
                .stream()
                .count();

        return  negativeNumbers;
    }

    @Override
    public long findPositiveQuantity(CustomArray array) throws WrongDataException {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);
        long positiveNumbers = stream.filter(x->x > 0)
                .findAny()
                .stream()
                .count();

        return positiveNumbers;
    }

    @Override
    public CustomArray replaceAllNegativeAndNullNumbersToZero(CustomArray array) throws WrongDataException {
        Integer[] numbers = array.getArray();
        IntStream stream = (IntStream) Arrays.stream(numbers);
        int[] newArray = stream.filter(x->x < 0)
                .findAny()
                .stream()
                .peek(x->x = 0)
                .toArray();
        CustomArray customArray = new CustomArray(newArray);

        return  customArray;
    }
}