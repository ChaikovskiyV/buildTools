package com.vchaikovsky.informationhanding.evaluator;

import com.vchaikovsky.informationhanding.entity.MathElement;
import com.vchaikovsky.informationhanding.entity.MathElementType;
import com.vchaikovsky.informationhanding.exception.HandingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ExpressionEvaluator {
    static final Logger logger = LogManager.getLogger();
    private String expression;

    public ExpressionEvaluator(String expression) {
        this.expression = expression;
    }

    public int evaluate() {
        ExpressionAnalysis analysis = new ExpressionAnalysis();
        List<MathElement> elements = analysis.analyze(expression);
        MathElementBuffer elementBuffer = new MathElementBuffer(elements);
        return expr(elementBuffer);
    }

    private int expr(MathElementBuffer elementBuffer) {
        MathElement element = elementBuffer.next();
        if(element.getType() == MathElementType.EXPR_END) {
            return 0;
        } else {
            elementBuffer.back();
            return or(elementBuffer);
        }
    }

    private int or(MathElementBuffer elementBuffer) {
        int number = xor(elementBuffer);
        MathElement element = elementBuffer.next();
        if(element.getType() == MathElementType.OR) {
            number = number | xor(elementBuffer);
            } else {
            elementBuffer.back();
            }
        return number;
    }

    private int xor(MathElementBuffer elementBuffer) {
        int number = and(elementBuffer);
        MathElement element = elementBuffer.next();
        if(element.getType() == MathElementType.XOR) {
            number = number ^ and(elementBuffer);
            } else {
            elementBuffer.back();
            }
        return number;
    }

    private int and(MathElementBuffer elementBuffer) {
        int number = shift(elementBuffer);
        MathElement element = elementBuffer.next();
        if(element.getType() == MathElementType.AND) {
            number = number & shift(elementBuffer);
            } else {
            elementBuffer.back();
            }
        return number;
    }

    private int shift(MathElementBuffer elementBuffer) {
        int number = plusMinus(elementBuffer);
        MathElement element = elementBuffer.next();
        switch (element.getType()) {
            case LEFT_SHIFT -> number = number << plusMinus(elementBuffer);
            case RIGHT_SHIFT -> number = number >> plusMinus(elementBuffer);
            default -> elementBuffer.back();
        }
        return number;
    }

    private int plusMinus(MathElementBuffer elementBuffer) {
        int number = mulDiv(elementBuffer);
        MathElement element = elementBuffer.next();
        switch (element.getType()) {
            case PLUS -> number = number + mulDiv(elementBuffer);
            case MINUS -> number = number - mulDiv(elementBuffer);
            default -> elementBuffer.back();
        }
        return number;
    }

    private int mulDiv(MathElementBuffer elementBuffer) {
        int number = findNumber(elementBuffer);
        MathElement element = elementBuffer.next();
        switch (element.getType()) {
            case DIV -> number = number / findNumber(elementBuffer);
            case MUL -> number = number * findNumber(elementBuffer);
            default -> elementBuffer.back();
        }
        return number;
    }

    private int findNumber(MathElementBuffer elementBuffer) {
        MathElement element = elementBuffer.next();
        int number = 0;
        switch (element.getType()) {
            case NUMBER -> number = Integer.parseInt(element.getElement());
            case MINUS -> number = - findNumber(elementBuffer);
            case NOT ->  number = ~ findNumber(elementBuffer);
            case LEFT_BRACKET -> {
                number = expr(elementBuffer);
                element = elementBuffer.next();
                if(element.getType() != MathElementType.RIGHT_BRACKET && element.getType() != MathElementType.EXPR_END) {
                    try {
                        throw new HandingException("The expression " + expression + " contains unknown symbol: " + element.getElement());
                    } catch (HandingException e) {
                        logger.error("The expression " + expression + " contains unknown symbol: " + element.getElement(), e);
                    }
                }
            }
        }
        return number;
    }
}