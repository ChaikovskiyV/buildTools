package com.vchaikovsky.informationhanding.parser;

import com.vchaikovsky.informationhanding.entity.TextComponent;
import com.vchaikovsky.informationhanding.entity.TextComponentType;
import com.vchaikovsky.informationhanding.entity.impl.SymbolLeaf;
import com.vchaikovsky.informationhanding.entity.impl.TextComposite;
import com.vchaikovsky.informationhanding.evaluator.ExpressionEvaluator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ExpressionParser extends AbstractParser{
    static final Logger logger = LogManager.getLogger();
    static final String DELIMITER = "";

    @Override
    TextComponent parse(String text) {
        TextComponent expressionComposite = new TextComposite(TextComponentType.EXPRESSION);
        ExpressionEvaluator evaluator = new ExpressionEvaluator(text);
        int number = evaluator.evaluate();
        String strNumber = String.valueOf(number);
        if(strNumber.length() > 1) {
            String[] digits = strNumber.split(DELIMITER);
            for (String digit : digits) {
                SymbolLeaf symbol = new SymbolLeaf(TextComponentType.DIGIT, digit.charAt(0));
                expressionComposite.add(symbol);
            }
        } else {
            SymbolLeaf symbol = new SymbolLeaf(TextComponentType.DIGIT, strNumber.charAt(0));
            expressionComposite.add(symbol);
        }
        return expressionComposite;
    }
}