package com.vchaikovsky.shapes.parser;

import com.vchaikovsky.shapes.exception.ShapeException;

import java.util.List;

public interface PyramidParameterParserInt {

    List<double[]> parseStrToPyramidParam(List<String> list) throws ShapeException;
}
