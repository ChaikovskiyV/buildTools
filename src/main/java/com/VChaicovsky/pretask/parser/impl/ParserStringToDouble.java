package com.VChaicovsky.pretask.parser.impl;

import com.VChaicovsky.pretask.exception.WrongDataException;
import com.VChaicovsky.pretask.parser.ParserStringToDoubleInt;
import com.VChaicovsky.pretask.validation.impl.DataValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParserStringToDouble implements ParserStringToDoubleInt {
    private final static  Logger logger = LogManager.getLogger();

    @Override
    public Double pasrseStrToDouble(String str) {
        Double number = null;
        try {
            if(new DataValidation().validateData(str)){
                number = Double.parseDouble(str);
            }
            }catch (NumberFormatException e){
            logger.log(Level.ERROR, new WrongDataException("Not correct data", e));
        }
        return number;
    }
}
