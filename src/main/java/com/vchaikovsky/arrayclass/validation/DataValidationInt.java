package com.vchaikovsky.arrayclass.validation;

import com.vchaikovsky.arrayclass.exceptions.WrongDataException;

public interface DataValidationInt {
    boolean validateArray(Integer[] array) throws WrongDataException;

    boolean isCorrectData(String str);

    boolean isIncludesNumbers(String str);
}
