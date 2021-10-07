package com.VChaicovsky.pretask.validation.impl;

import com.VChaicovsky.pretask.reader.impl.ReaderFromFile;
import com.VChaicovsky.pretask.validation.DataValidationInt;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation implements DataValidationInt {
private final static  String R_EX = "[+-]?\\d*[\\.]?\\d+";

    @Override
    public boolean fileIsEmpty(String filename) {
        return new ReaderFromFile().readArrayFromFile(filename) != null;
    }

    @Override
    public boolean checkQuantityNumbers(String[] strArray) {
        int minLengthArray = 2;
        return strArray.length >= minLengthArray;
    }

    @Override
    public boolean validateData(String string) {
        Pattern pattern = Pattern.compile(R_EX);
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

    @Override
    public boolean fileFound(String str){
        return Files.exists(Path.of(str));
    }
}