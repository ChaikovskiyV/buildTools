package com.vchaikovsky.informationhanding.evaluator;

import com.vchaikovsky.informationhanding.entity.MathElement;
import com.vchaikovsky.informationhanding.entity.MathElementType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionAnalysis {
    static final Logger logger = LogManager.getLogger();
    static final String DELIMITER = "";
    static final String MINUS = "-";
    static final String PLUS = "+";
    static final String DIV = "/";
    static final String MUL = "*";
    static final String NOT = "~";
    static final String OR = "|";
    static final String AND = "&";
    static final String XOR = "^";
    static final String LEFT_SHIFT = "<<";
    static final String RIGHT_SHIFT = ">>";
    static final String LEFT_BRACKET = "(";
    static final String RIGHT_BRACKET = ")";

    public List<MathElement> analyze(String expression) {
        List<MathElement> elements = new ArrayList<>();
        List<String> symbols = Arrays.asList(expression.split(DELIMITER));
        StringBuilder sb = new StringBuilder();
        symbols.forEach(s -> {
            char symbol = s.charAt(0);
            if(Character.isDigit(symbol)) {
                sb.append(s);
            } else {
                if(!sb.isEmpty() && Character.isDigit(sb.charAt(0))) {
                    elements.add(new MathElement(MathElementType.NUMBER, sb.toString()));
                    sb.delete(0, sb.length());
                }
                switch (s) {
                    case MINUS -> elements.add(new MathElement(MathElementType.MINUS, s));
                    case PLUS -> elements.add(new MathElement(MathElementType.PLUS, s));
                    case DIV -> elements.add(new MathElement(MathElementType.DIV, s));
                    case MUL -> elements.add(new MathElement(MathElementType.MUL, s));
                    case NOT -> elements.add(new MathElement(MathElementType.NOT, s));
                    case OR -> elements.add(new MathElement(MathElementType.OR, s));
                    case AND -> elements.add(new MathElement(MathElementType.AND, s));
                    case XOR -> elements.add(new MathElement(MathElementType.XOR, s));
                    case LEFT_BRACKET -> elements.add(new MathElement(MathElementType.LEFT_BRACKET, s));
                    case RIGHT_BRACKET -> elements.add(new MathElement(MathElementType.RIGHT_BRACKET, s));
                    default -> {
                        sb.append(s);
                        if (sb.length() == 2) {
                            MathElementType elementType = sb.toString().equals(LEFT_SHIFT) ? MathElementType.LEFT_SHIFT : MathElementType.RIGHT_SHIFT;
                            elements.add(new MathElement(elementType, sb.toString()));
                            sb.delete(0, sb.length());
                        }
                    }
                }
            }
        });
        if(!sb.isEmpty() && Character.isDigit(sb.charAt(0))) {
            elements.add(new MathElement(MathElementType.NUMBER, sb.toString()));
        }
        elements.add(new MathElement(MathElementType.EXPR_END, DELIMITER));

        return elements;
    }
}