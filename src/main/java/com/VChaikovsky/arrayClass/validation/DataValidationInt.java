package com.VChaikovsky.arrayClass.validation;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;

public interface DataValidationInt {
    boolean validateArray(Integer[] array) throws WrongDataException;

    boolean isCorrectData(String str);

    boolean isIncludesNumbers(String str);
}
