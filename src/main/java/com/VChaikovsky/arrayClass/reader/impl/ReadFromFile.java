package com.VChaikovsky.arrayClass.reader.impl;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.reader.ReaderFromFileInt;
import com.VChaikovsky.arrayClass.validation.impl.DataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFromFile implements ReaderFromFileInt {
    final static Logger logger = LogManager.getLogger();

    @Override
    public String readString(String filename) throws WrongDataException {
        String str = null;
    try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))){
        DataValidation validation = new DataValidation();
        while (reader.ready()){
            str = reader.readLine();
            if(validation.isCorrectData(str) && validation.isIncludesNumbers(str)){
                break;
            } else {
                str = null;
            }
        }
        if(str == null){
            throw new WrongDataException("The file "+filename+" contains wrong data");
        }
    } catch (IOException e){
        logger.error(new WrongDataException("The file "+ filename+" was not found.", e));
    }
        return str;
    }
}