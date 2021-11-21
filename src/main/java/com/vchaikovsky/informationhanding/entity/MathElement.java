package com.vchaikovsky.informationhanding.entity;

public class MathElement {
    private MathElementType type;
    private String element;

    public MathElement(MathElementType type, String element) {
        this.type = type;
        this.element = element;
    }

    public MathElement(MathElementType type, char element) {
        this.type = type;
        this.element = String.valueOf(element);
    }

    public MathElementType getType() {
        return type;
    }

    public String getElement() {
        return element;
    }
}