package com.vchaikovsky.arrayclass.reader.impl;

import com.vchaikovsky.arrayclass.reader.ReaderFromFileInt;
import com.vchaikovsky.arrayclass.exceptions.WrongDataException;
import com.vchaikovsky.arrayclass.validation.impl.DataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromFile implements ReaderFromFileInt {
    static final Logger logger = LogManager.getLogger();

    @Override
    public String readString(String filename) throws WrongDataException {
        String str = null;
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))){
        DataValidation validation = new DataValidation();
        while (reader.ready()){
            str = reader.readLine();
            if(validation.isCorrectData(str) && validation.isIncludesNumbers(str)) {
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