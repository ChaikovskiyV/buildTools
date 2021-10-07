package com.VChaicovsky.pretask.creater;

import com.VChaicovsky.pretask.entity.CustomNumber;
import com.VChaicovsky.pretask.parser.impl.ParserStringToDouble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumbersFactory {
    final static Logger logger = LogManager.getLogger();

    public CustomNumber createNumber(String str){

        return new CustomNumber(new ParserStringToDouble().pasrseStrToDouble(str));
    }

}