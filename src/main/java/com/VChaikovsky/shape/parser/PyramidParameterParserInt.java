package com.vchaikovsky.shape.parser;

import com.vchaikovsky.shape.exception.ShapeException;

import java.util.List;

public interface PyramidParameterParserInt {

    List<double[]> parseStrToPyramidParam(List<String> list) throws ShapeException;
}
