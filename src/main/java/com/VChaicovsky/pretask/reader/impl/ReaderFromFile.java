package com.VChaicovsky.pretask.reader.impl;

import com.VChaicovsky.pretask.exception.WrongDataException;
import com.VChaicovsky.pretask.reader.ReaderFromFileInt;
import com.VChaicovsky.pretask.validation.impl.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderFromFile implements ReaderFromFileInt {
final static Logger logger = LogManager.getLogger();

    public String[] readArrayFromFile(String fileName){
        String[] stringsArray = null;
        boolean fileFound = new DataValidation().fileFound(fileName);
        if(fileFound){
            try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))){
                if(reader.ready()){
                    stringsArray = new String[2];
                }
                int i = 0;
                while (reader.ready() && i < 2 && stringsArray != null){
                    stringsArray[i] = reader.readLine();
                    i++;
                }
            }catch (IOException e) {
                logger.log(Level.ERROR, new WrongDataException("Such file was not found.", e));
            }
        }

        return stringsArray;
    }
}