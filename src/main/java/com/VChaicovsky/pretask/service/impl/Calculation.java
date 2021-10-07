package com.VChaicovsky.pretask.service.impl;

import com.VChaicovsky.pretask.entity.CustomNumber;
import com.VChaicovsky.pretask.service.CalculationInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculation implements CalculationInt {
    private final static Logger logger = LogManager.getLogger();

    public CustomNumber addition(CustomNumber numberOne, CustomNumber numberTwo){
        return new CustomNumber(numberOne.getNumber() + numberTwo.getNumber());
    }

    public CustomNumber subtraction(CustomNumber numberOne, CustomNumber numberTwo){
        return new CustomNumber(numberOne.getNumber() - numberTwo.getNumber());
    }

    public CustomNumber multiplication(CustomNumber numberOne, CustomNumber numberTwo){
        return new CustomNumber(numberOne.getNumber() * numberTwo.getNumber());
    }

    public CustomNumber division(CustomNumber numberOne, CustomNumber numberTwo){
        return new CustomNumber(numberOne.getNumber() / numberTwo.getNumber());
    }
}