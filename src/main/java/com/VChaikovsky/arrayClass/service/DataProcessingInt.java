package com.VChaikovsky.arrayClass.service;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;

public interface DataProcessingInt {

    Integer findMin(CustomArray array) throws WrongDataException;

    Integer findMax(CustomArray array) throws WrongDataException;

    double findAverage(CustomArray array) throws WrongDataException;

    int findNumbersAmount(CustomArray array) throws WrongDataException;

    int findNegativeQuantity(CustomArray array) throws WrongDataException;

    int findPositiveQuantity(CustomArray array) throws WrongDataException;

    CustomArray replaceAllNegativeNumbersToZero(CustomArray array) throws WrongDataException;
}
