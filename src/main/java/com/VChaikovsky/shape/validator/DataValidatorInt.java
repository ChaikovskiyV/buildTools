package com.vchaikovsky.shape.validator;

import com.vchaikovsky.shape.entity.impl.Point;
import com.vchaikovsky.shape.entity.impl.Pyramid;

public interface DataValidatorInt {

    boolean isValidString(String str);

    boolean isValidParam(String[] parametersArray);

    boolean isValidStringNumber(String stringNumber);

    boolean isDiffPoints(Point basesCenter, Point peak);

    boolean isValidRadius(double radius);

    boolean isValidCornersNumber(int cornersNumber);

    boolean isValidPyramid(Pyramid pyramid);
}