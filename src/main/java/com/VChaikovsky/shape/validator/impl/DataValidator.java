package com.vchaikovsky.shape.validator.impl;

import com.vchaikovsky.shape.entity.impl.Point;
import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.validator.DataValidatorInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator implements DataValidatorInt {
    static final Logger logger = LogManager.getLogger();
    private static final String STRING_REGEX = "[-\\s\\.\\d]+";
    private static final String STRING_NUMBER_REGEX = "-?\\d+\\.?\\d*";
    private static DataValidator instance;

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
        int count = 0;
        int arrayLength = 8;

        if(array.length == arrayLength) {
            while (count < array.length && isValidStringNumber(array[count])) {
                count++;
            }
        }
        return  count == array.length;
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
        return isDiffPoints(pyramid.getBasesCenter(), pyramid.getPeak()) && isParallelAxis(pyramid) &&
                isValidRadius(pyramid.getCircumcircleRadius()) && isValidCornersNumber(pyramid.getBasesCornersNumber());
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