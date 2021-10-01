package com.VChaikovsky.arrayClass.service;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import com.VChaikovsky.arrayClass.exceptions.WrongDataException;

public interface DataProcessingInt {

    Double findMin(CustomArray array) throws WrongDataException;

    Double findMax(CustomArray array) throws WrongDataException;

    Double findAverage(CustomArray array) throws WrongDataException;

    Double findNumbersAmount(CustomArray array) throws WrongDataException;

    int findNegativeQuantity(CustomArray array) throws WrongDataException;

    int findPositiveQuantity(CustomArray array) throws WrongDataException;

    CustomArray changeAllNegativeNumbers(CustomArray array) throws WrongDataException;
}
