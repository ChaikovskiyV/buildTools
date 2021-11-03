package com.VChaikovsky.arrayclass.service;

import com.VChaikovsky.arrayclass.entity.CustomArray;
import com.VChaikovsky.arrayclass.exceptions.WrongDataException;

public interface DataProcessingStreamInt {

    Integer findMin(CustomArray array) throws WrongDataException;

    Integer findMax(CustomArray array) throws WrongDataException;

    double findAverage(CustomArray array) throws WrongDataException;

    int findNumbersAmount(CustomArray array) throws WrongDataException;

    long findNegativeQuantity(CustomArray array) throws WrongDataException;

    long findPositiveQuantity(CustomArray array) throws WrongDataException;

    CustomArray replaceAllNegativeNumbersToZero(CustomArray array) throws WrongDataException;

}