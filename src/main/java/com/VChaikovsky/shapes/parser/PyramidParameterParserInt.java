package com.VChaikovsky.shapes.parser;

import com.VChaikovsky.shapes.exception.ShapeException;

import java.util.List;

public interface PyramidParameterParserInt {

    List<double[]> parseStrToPyramidParam(List<String> list) throws ShapeException;
}
