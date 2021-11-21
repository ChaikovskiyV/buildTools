package com.vchaikovsky.informationhanding.evaluator;

import com.vchaikovsky.informationhanding.entity.MathElement;

import java.util.List;

public class MathElementBuffer {
    List<MathElement> elements;
    int position;

    public MathElementBuffer(List<MathElement> elements) {
        this.elements = elements;
    }

    public MathElement next() {
        return elements.get(position++);
    }

    public void back() {
        position--;
    }

    public int getPosition() {
        return position;
    }
}