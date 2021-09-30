package com.VChaicovsky.pretask.service;

import com.VChaicovsky.pretask.entity.CustomNumber;

public interface CalculationInt {

    CustomNumber addition(CustomNumber numberOne, CustomNumber numberTwo);
    CustomNumber subtraction(CustomNumber numberOne, CustomNumber numberTwo);
    CustomNumber multiplication(CustomNumber numberOne, CustomNumber numberTwo);
    CustomNumber division(CustomNumber numberOne, CustomNumber numberTwo);
}
