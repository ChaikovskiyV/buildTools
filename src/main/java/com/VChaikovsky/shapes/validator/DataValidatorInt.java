package com.VChaikovsky.shapes.validator;

import com.VChaikovsky.shapes.entity.Point;
import com.VChaikovsky.shapes.entity.Pyramid;

public interface DataValidatorInt {

    boolean isValidString(String str);

    boolean isValidStringNumber(String stringNumber);

    boolean isDiffPoints(Point basesCenter, Point peak);

    boolean isValidRadius(double radius);

    boolean isValidCornersNumber(int cornersNumber);

    boolean isValidPyramid(Pyramid pyramid);
}