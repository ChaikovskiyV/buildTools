package com.VChaicovsky.pretask.service.impl;

import com.VChaicovsky.pretask.entity.CustomNumber;
import com.VChaicovsky.pretask.service.CalculationInt;

public class Calculation implements CalculationInt {

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