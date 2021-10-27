package com.vchaikovsky.shapes.reader;

import com.vchaikovsky.shapes.exception.ShapeException;

import java.util.List;

public interface ReaderFromFileInt {
    List<String> readData(String filepath) throws ShapeException;
}
