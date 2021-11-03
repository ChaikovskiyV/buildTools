package com.VChaikovsky.arrayclass.validation;

import com.VChaikovsky.arrayclass.exceptions.WrongDataException;

public interface DataValidationInt {
    boolean validateArray(Integer[] array) throws WrongDataException;

    boolean isCorrectData(String str);

    boolean isIncludesNumbers(String str);
}
