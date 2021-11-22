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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MathElement)) {
            return false;
        }
        MathElement newElement = (MathElement) o;
        return type == newElement.type && element.equals(newElement.element);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first * type.hashCode();
        result = result * first * element.hashCode();
        return result;
    }
}