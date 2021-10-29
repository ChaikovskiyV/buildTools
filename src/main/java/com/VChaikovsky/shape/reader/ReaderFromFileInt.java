package com.vchaikovsky.shape.reader;

import com.vchaikovsky.shape.exception.ShapeException;

import java.util.List;

public interface ReaderFromFileInt {
    List<String> readData(String filepath) throws ShapeException;
}
