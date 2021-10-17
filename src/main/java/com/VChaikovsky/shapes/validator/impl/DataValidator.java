package com.VChaikovsky.shapes.validator.impl;

import com.VChaikovsky.shapes.entity.impl.Point;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.validator.DataValidatorInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator implements DataValidatorInt {
    final static Logger logger = LogManager.getLogger();
    private static DataValidator instance;
    private final String STRING_REGEX = "[-\\s\\.\\d]+";
    private final String STRING_NUMBER_REGEX = "-?\\d+\\.?\\d*";

    private DataValidator() {}

    public static DataValidator getInstance() {
        if(instance == null) {
            instance = new DataValidator();
        }
        return instance;
    }

    @Override
    public boolean isValidString(String str) {
        Pattern pattern = Pattern.compile(STRING_REGEX);
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

    @Override
    public boolean isValidParam(String[] array) {
        boolean result = true;
        if(array.length == 8) {
            int count = 0;
            while (count < array.length) {
                if (isValidStringNumber(array[count])) {
                    count++;
                } else {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return  result;
    }

    public boolean isValidStringNumber(String stringNumber) {
        Pattern pattern = Pattern.compile(STRING_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(stringNumber);

        return matcher.matches();
    }

    @Override
    public boolean isDiffPoints(Point basesCenter, Point peak) {
        return !basesCenter.equals(peak);
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

    public boolean isParallelAxis(Pyramid pyramid){
        Point basesCenter = pyramid.getBasesCenter();
        Point peak = pyramid.getPeak();
        double gradX = basesCenter.x() - peak.x();
        double gradY = basesCenter.y() - peak.y();
        double gradZ = basesCenter.z() - peak.z();
        boolean isParallelX = gradY == 0 && gradZ == 0;
        boolean isParallelY = gradX == 0 && gradZ == 0;
        boolean isParallelZ = gradX == 0 && gradY == 0;

        return isParallelX || isParallelY || isParallelZ;
    }
}