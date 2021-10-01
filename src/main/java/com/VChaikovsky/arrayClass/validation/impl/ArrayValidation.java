package com.VChaikovsky.arrayClass.validation.impl;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import com.VChaikovsky.arrayClass.validation.ArrayValidationInt;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayValidation implements ArrayValidationInt {
    final static Logger logger = LogManager.getLogger();

    public boolean validateArray(Double[] array) throws WrongDataException {
        boolean validateResult = false;
        if(array != null){
            validateResult = true;
        }
        else {
            try {
                throw new WrongDataException("The array is null");
                //logger.throwing(Level.ERROR, new WrongDataException("The array is null"));
            } catch (NullPointerException e){
                logger.log(Level.ERROR, e);
            }
        }
        return validateResult;
    }
}
