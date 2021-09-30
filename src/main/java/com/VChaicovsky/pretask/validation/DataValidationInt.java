package com.VChaicovsky.pretask.validation;

public interface DataValidationInt {

    boolean fileIsEmpty(String filename);

    boolean checkQuantityNumbers(String[] strArray);

    boolean validateData(String str);

    boolean fileFound(String filename);


}
