package com.VChaikovsky.shapes.validator.impl;

import com.VChaikovsky.shapes.entity.Point;
import com.VChaikovsky.shapes.entity.Pyramid;
import com.VChaikovsky.shapes.validator.DataValidatorInt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator implements DataValidatorInt {
    private final static String STRING_REGEX = "[-\\s\\.\\d]+";
    private final static String STRING_NUMBER_REGEX = "^-?\\d+\\.?\\d*";

    @Override
    public boolean isValidString(String str) {
        Pattern pattern = Pattern.compile(STRING_REGEX);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

    @Override
    public boolean isValidStringNumber(String stringNumber) {
        Pattern pattern = Pattern.compile(STRING_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(stringNumber);

        return matcher.matches();
    }

    @Override
    public boolean isDiffPoints(Point basesCenter, Point peak) {
        return basesCenter.equals(peak) == false;
    }

    @Override
    public boolean isValidRadius(double radius) {
        return radius > 0;
    }

    @Override
    public boolean isValidCornersNumber(int cornersNumber) {
        return cornersNumber > 2;
    }

    @Override
    public boolean isValidPyramid(Pyramid pyramid) {
        boolean result = true;
        if(!isDiffPoints(pyramid.getBasesCenter(), pyramid.getPeak()) || !isParallelAxis(pyramid) ||
                !isValidRadius(pyramid.getCircumcircleRadius()) || !isValidCornersNumber(pyramid.getBasesCornersNumber())){
            result = false;
        }
        return result;
    }

    private boolean isParallelAxis(Pyramid pyramid){
        double gradX = pyramid.getBasesCenterX() - pyramid.getPeakX();
        double gradY = pyramid.getBasesCenterY() - pyramid.getPeakY();
        double gradZ = pyramid.getBasesCenterZ() - pyramid.getPeakZ();
        boolean isParallelX = gradY == 0 && gradZ == 0;
        boolean isParallelY = gradX == 0 && gradZ == 0;
        boolean isParallelZ = gradX == 0 && gradY == 0;

        return isParallelX || isParallelY || isParallelZ;
    }
}