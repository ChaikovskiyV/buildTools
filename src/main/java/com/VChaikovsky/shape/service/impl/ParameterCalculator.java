package com.vchaikovsky.shape.service.impl;

import com.vchaikovsky.shape.entity.impl.Point;
import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.exception.ShapeException;
import com.vchaikovsky.shape.service.ParametersCalculatorInt;
import com.vchaikovsky.shape.validator.impl.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParameterCalculator implements ParametersCalculatorInt {
    static final Logger logger = LogManager.getLogger();
    private static ParameterCalculator instance;

    private ParameterCalculator() {}

    public static ParameterCalculator getInstance() {
        if(instance == null) {
            instance = new ParameterCalculator();
        }
        return instance;
    }

    @Override
    public double findVolume(Pyramid pyramid) throws ShapeException {
        DataValidator validator = DataValidator.getInstance();
        double volume;

        if (!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid + " is not correct.");
            throw new ShapeException("The " + pyramid + " is not correct.");
        }
            double high = findPyramidHeight(pyramid);
            double sideLength = findBasesSideLength(pyramid);
            int cornersNumber = pyramid.getBasesCornersNumber();

            volume = cornersNumber * Math.pow(sideLength, 2) * high / (12 * Math.tan(Math.PI / cornersNumber));

        return volume;
    }

    @Override
    public double findSurfaceSquare(Pyramid pyramid) throws ShapeException {
        DataValidator validator = DataValidator.getInstance();

        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid + " is not correct.");
            throw new ShapeException("The " + pyramid + " is not correct.");
        }
            double height = findPyramidHeight(pyramid);
            double radius = pyramid.getCircumcircleRadius();
            int cornersNumber = pyramid.getBasesCornersNumber();

        double square = cornersNumber * radius * Math.sin(Math.PI / cornersNumber) * (radius * Math.cos(Math.PI / cornersNumber) +
                    Math.sqrt(Math.pow(height, 2) + Math.pow(radius * Math.cos(Math.PI / cornersNumber), 2)));

        return square;
    }

    @Override
    public String findVolumeProportion(Pyramid pyramid) throws ShapeException {
        DataValidator validator = DataValidator.getInstance();
        String result = "The " + pyramid + " is not crossed by any basic plane.";

        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid+" is not correct.");
            throw new ShapeException("The " + pyramid+" is not correct.");
        }
            Point basesCenter = pyramid.getBasesCenter();
            Point peak = pyramid.getPeak();

            if(!isBasesOnBasicPlane(pyramid) && !isPointOnBasicPlane(peak)){
                Point planePoint;
                int[] volumeProportion;

                if(findParallelAxis(pyramid).equals("X") && (basesCenter.x() / peak.x()) < 0){
                    planePoint = new Point(0, basesCenter.y(), basesCenter.z());
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + " is divided by YZ plane as " + volumeProportion[0] +
                            " : " + volumeProportion[1] + ".";
                }
                else if(findParallelAxis(pyramid).equals("Y") && (basesCenter.y() / peak.y()) < 0){
                    planePoint = new Point(basesCenter.x(), 0, basesCenter.z());
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + " is divided by XZ plane as " + volumeProportion[0] +
                            " : " + volumeProportion[1] + ".";
                }
                else if(findParallelAxis(pyramid).equals("Z") && (basesCenter.z() / peak.z()) < 0){
                    planePoint = new Point(basesCenter.x(), basesCenter.y(), 0);
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + " is divided by XY plane as " + volumeProportion[0] +
                            " : " + volumeProportion[1] + ".";
                }
            }
        return result;
    }

    @Override
    public boolean isBasesOnBasicPlane(Pyramid pyramid) throws ShapeException {
        DataValidator validator = DataValidator.getInstance();
        boolean result = false;

        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid +" is not correct.");
            throw new ShapeException("The " + pyramid +" is not correct.");
        }
            Point basesCenter = pyramid.getBasesCenter();

            if(isPointOnBasicPlane(basesCenter)) {
                result = true;
            }
        return result;
    }

    public double findPyramidHeight(Pyramid pyramid) throws ShapeException {
        DataValidator validator = DataValidator.getInstance();

        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid +" is not correct.");
            throw new ShapeException("The " + pyramid +" is not correct.");
        }

        Point basesCenter = pyramid.getBasesCenter();
        Point peak = pyramid.getPeak();

        double height = Math.sqrt(Math.pow(basesCenter.x() - peak.x(), 2) +
                Math.pow(basesCenter.y() - peak.y(), 2) +
                Math.pow(basesCenter.z() - peak.z(), 2));

        return height;
    }

    public double findBasesSideLength(Pyramid pyramid) throws ShapeException {
        DataValidator validator = DataValidator.getInstance();

        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid +" is not correct.");
            throw new ShapeException("The " + pyramid +" is not correct.");
        }

        double radius = pyramid.getCircumcircleRadius();
        int cornersNumber = pyramid.getBasesCornersNumber();

        double sideLength = 2 * radius * Math.sin(Math.PI / cornersNumber);

        return sideLength;
    }

    public double findDistanceToZeroPoint(Point point) {
        double distance = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2) + Math.pow(point.z(), 2));

        return distance;
    }

    private boolean isPointOnBasicPlane(Point point){
        return point.x() == 0 || point.y() == 0 || point.z() == 0;
    }

    private String findParallelAxis(Pyramid pyramid){
        DataValidator validator = DataValidator.getInstance();
        String parallelAxis = null;

        if(validator.isParallelAxis(pyramid)){
            Point basesCenter = pyramid.getBasesCenter();
            Point peak = pyramid.getPeak();

            if(basesCenter.x() != peak.x()){
                parallelAxis = "X";
            } else if(basesCenter.y() != peak.y()){
                parallelAxis = "Y";
            } else if(basesCenter.z() != peak.z()){
                parallelAxis = "Z";
            }
        }
        return parallelAxis;
    }

    private int[] calculateVolumeProportional(Pyramid pyramid, Point newCenterPoint) throws ShapeException {
        int whole = 100;
        double newHigh = Math.sqrt(Math.pow(pyramid.getBasesCenter().x() - newCenterPoint.x(), 2) +
                Math.pow(pyramid.getBasesCenter().y() - newCenterPoint.y(), 2) +
                Math.pow(pyramid.getBasesCenter().z() - newCenterPoint.z(), 2));
        double newRadius = newHigh * pyramid.getCircumcircleRadius() / findPyramidHeight(pyramid);
        double newSideLength = 2 *newRadius * Math.sin(Math.PI / pyramid.getBasesCornersNumber());

        double newVolume = pyramid.getBasesCornersNumber() * Math.pow(newSideLength, 2) * newHigh / (12 * Math.tan(Math.PI / pyramid.getBasesCornersNumber()));

        int upperPartPercent = (int) (whole * newVolume / findVolume(pyramid));
        int lowerPartPercent = whole - upperPartPercent;

        return new int[]{upperPartPercent, lowerPartPercent};
    }
}