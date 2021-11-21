package com.vchaikovsky.informationhanding.entity;

public enum MathElementType {
    MINUS("-"),
    PLUS("+"),
    DIV("/"),
    MUL("*"),
    NOT("~"),
    OR("|"),
    AND("&"),
    XOR("^"),
    LEFT_SHIFT("<<"),
    RIGHT_SHIFT(">>"),
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")"),
    NUMBER("//d+"),
    EXPR_END("");
    private String symbol;

    MathElementType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}