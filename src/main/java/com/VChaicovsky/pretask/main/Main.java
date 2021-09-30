package com.VChaicovsky.pretask.main;

import com.VChaicovsky.pretask.entity.CustomNumber;
import com.VChaicovsky.pretask.parser.impl.ParserStringToDouble;

import com.VChaicovsky.pretask.reader.impl.ReaderFromFile;
import com.VChaicovsky.pretask.service.impl.Calculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ParserStringToDouble parserStringToDouble = new ParserStringToDouble();
        Calculation calculation = new Calculation();
        ReaderFromFile reader = new ReaderFromFile();

        String fileName = "sources/numbers.txt";
        String[] strings = reader.readArrayFromFile(fileName);
        double numberOne = parserStringToDouble.pasrseStrToDouble(strings[0]);
        double numberTwo = parserStringToDouble.pasrseStrToDouble(strings[1]);
        CustomNumber customNumberOne = new CustomNumber(numberOne);
        CustomNumber customNumberTwo = new CustomNumber(numberTwo);

        logger.info("The results of calculations with numbers "+ numberOne+" and "+numberTwo+" are:"+"\n"+
        "- additional: "+ calculation.addition(customNumberOne, customNumberTwo)+
        "\n- subtraction: "+ calculation.subtraction(customNumberOne, customNumberTwo)+
                "\n- division: "+ calculation.division(customNumberOne, customNumberTwo)+
                "\n- multiplication: "+ calculation.multiplication(customNumberOne, customNumberTwo));
    }
}
