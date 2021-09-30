package com.VChaicovsky.pretask.creater;

import com.VChaicovsky.pretask.entity.CustomNumber;
import com.VChaicovsky.pretask.parser.impl.ParserStringToDouble;
import com.VChaicovsky.pretask.validation.impl.DataValidation;

public class NumbersFactory {

    public CustomNumber createNumber(String str){

        return new CustomNumber(new ParserStringToDouble().pasrseStrToDouble(str));
    }

}