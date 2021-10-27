package com.vchaikovsky.shapes.validator;

import com.vchaikovsky.shapes.entity.impl.Point;
import com.vchaikovsky.shapes.entity.impl.Pyramid;

public interface DataValidatorInt {

    boolean isValidString(String str);

    boolean isValidParam(String[] parametersArray);

    boolean isValidStringNumber(String stringNumber);

    boolean isDiffPoints(Point basesCenter, Point peak);

    boolean isValidRadius(double radius);

    boolean isValidCornersNumber(int cornersNumber);

    boolean isValidPyramid(Pyramid pyramid);
}