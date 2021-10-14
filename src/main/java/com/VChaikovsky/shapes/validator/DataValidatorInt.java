package com.VChaikovsky.shapes.validator;

import com.VChaikovsky.shapes.entity.impl.Point;
import com.VChaikovsky.shapes.entity.impl.Pyramid;

public interface DataValidatorInt {

    boolean isValidString(String str);

    boolean isValidParam(String[] parametersArray);

    boolean isValidStringNumber(String stringNumber);

    boolean isDiffPoints(Point basesCenter, Point peak);

    boolean isValidRadius(double radius);

    boolean isValidCornersNumber(int cornersNumber);

    boolean isValidPyramid(Pyramid pyramid);
}