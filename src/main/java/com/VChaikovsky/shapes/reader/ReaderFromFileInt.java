package com.VChaikovsky.shapes.reader;

import com.VChaikovsky.shapes.exception.ShapeException;

import java.util.List;

public interface ReaderFromFileInt {
    List<String> readData(String filepath) throws ShapeException;
}
