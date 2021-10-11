package com.VChaikovsky.shapes.main;

import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.reader.impl.ReaderFromFile;

import java.util.List;

public class Main {
    public static void main(String[] args) throws ShapeException {
        ReaderFromFile reader = new ReaderFromFile();
        List<String> list = reader.readData("pyramid.txt");
        list.forEach(System.out :: println);
    }
}
