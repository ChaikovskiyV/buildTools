package com.VChaikovsky.shapes.service.impl;

import com.VChaikovsky.shapes.entity.impl.Point;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.service.ParametersCalculatorInt;
import com.VChaikovsky.shapes.validator.impl.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParameterCalculator implements ParametersCalculatorInt {
    final static Logger logger = LogManager.getLogger();
    private DataValidator validator = new DataValidator();

    @Override
    public double findVolume(Pyramid pyramid) throws ShapeException {
        double volume;
        if (!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid + " is not pyramid.");
            throw new ShapeException("The " + pyramid + " is not pyramid.");
        }
            double high = findPyramidHeight(pyramid);
            double sideLength = findBasesSideLength(pyramid);
            int cornersNumber = pyramid.getBasesCornersNumber();

            volume = cornersNumber * Math.pow(sideLength, 2) * high / (12 * Math.tan(Math.PI / cornersNumber));

        return volume;
    }

    @Override
    public double findSurfaceSquare(Pyramid pyramid) throws ShapeException {
        double square;
        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid + " is not pyramid.");
            throw new ShapeException("The " + pyramid + " is not pyramid.");
        }
            double height = findPyramidHeight(pyramid);
            double radius = pyramid.getCircumcircleRadius();
            int cornersNumber = pyramid.getBasesCornersNumber();

            square = cornersNumber * radius * Math.sin(Math.PI / cornersNumber) * (radius * Math.cos(Math.PI / cornersNumber) +
                    Math.sqrt(Math.pow(height, 2) + Math.pow(radius * Math.cos(Math.PI / cornersNumber), 2)));

        return square;
    }

    @Override
    public String findVolumeProportion(Pyramid pyramid) throws ShapeException {
        String result = "The " + pyramid + " is not crossed by any basic plane.";

        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid+" is not pyramid.");
            throw new ShapeException("The " + pyramid+" is not pyramid.");
        }
            Point basesCenter = pyramid.getBasesCenter();
            Point peak = pyramid.getPeak();

            if(!isBasesOnBasePlane(pyramid) && !isPointOnBasicPlane(peak)){
                Point planePoint;
                int[] volumeProportion;

                if(findParallelAxis(pyramid).equals("X") && (basesCenter.getX() / peak.getX()) < 0){
                    planePoint = new Point(0, basesCenter.getY(), basesCenter.getZ());
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + " is divided by YZ plane as " + volumeProportion[0] +
                            " : " + volumeProportion[1] + ".";
                }
                else if(findParallelAxis(pyramid).equals("Y") && (basesCenter.getY() / peak.getY()) < 0){
                    planePoint = new Point(basesCenter.getX(), 0, basesCenter.getZ());
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + " is divided by XZ plane as " + volumeProportion[0] +
                            " : " + volumeProportion[1] + ".";
                }
                else if(findParallelAxis(pyramid).equals("Z") && (basesCenter.getZ() / peak.getZ()) < 0){
                    planePoint = new Point(basesCenter.getX(), basesCenter.getY(), 0);
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + " is divided by XY plane as " + volumeProportion[0] +
                            " : " + volumeProportion[1] + ".";
                }
            }
        return result;
    }

    @Override
    public boolean isBasesOnBasePlane(Pyramid pyramid) throws ShapeException {
        boolean result = false;

        if(!validator.isValidPyramid(pyramid)) {
            logger.error("The " + pyramid +" is not pyramid.");
            throw new ShapeException("The " + pyramid +" is not pyramid.");
        }
            Point basesCenter = pyramid.getBasesCenter();
            if(isPointOnBasicPlane(basesCenter)) {
                result = true;
            }
        return result;
    }

    public double findPyramidHeight(Pyramid pyramid) {
        Point basesCenter = pyramid.getBasesCenter();
        Point peak = pyramid.getPeak();

        double height = Math.sqrt(Math.pow(basesCenter.getX() - peak.getX(), 2) +
                Math.pow(basesCenter.getY() - peak.getY(), 2) +
                Math.pow(basesCenter.getZ() - peak.getZ(), 2));

        return height;
    }

    private double findBasesSideLength(Pyramid pyramid) {
        double radius = pyramid.getCircumcircleRadius();
        int cornersNumber = pyramid.getBasesCornersNumber();

        double sideLength = 2 * radius * Math.sin(Math.PI / cornersNumber);

        return sideLength;
    }

    private boolean isPointOnBasicPlane(Point point){
        return point.getX() == 0 || point.getY() == 0 || point.getZ() == 0;
    }

    private String findParallelAxis(Pyramid pyramid){
        String parallelAxis = null;
        if(validator.isParallelAxis(pyramid)){
            Point basesCenter = pyramid.getBasesCenter();
            Point peak = pyramid.getPeak();
            if(basesCenter.getX() != peak.getX()){
                parallelAxis = "X";
            } else if(basesCenter.getY() != peak.getY()){
                parallelAxis = "Y";
            } else if(basesCenter.getZ() != peak.getZ()){
                parallelAxis = "Z";
            }
        }
        return parallelAxis;
    }

    private int[] calculateVolumeProportional(Pyramid pyramid, Point newCenterPoint) throws ShapeException {
        int whole = 100;
        double newHigh = Math.sqrt(Math.pow(pyramid.getBasesCenter().getX() - newCenterPoint.getX(), 2) +
                Math.pow(pyramid.getBasesCenter().getY() - newCenterPoint.getY(), 2) +
                Math.pow(pyramid.getBasesCenter().getZ() - newCenterPoint.getZ(), 2));
        double newRadius = newHigh * pyramid.getCircumcircleRadius() / findPyramidHeight(pyramid);

        Pyramid newPyramid = new Pyramid(newCenterPoint, pyramid.getPeak(), pyramid.getBasesCornersNumber(), newRadius);

        int upperPartPercent = (int) (whole * findVolume(newPyramid) / findVolume(pyramid));
        int lowerPartPercent = whole - upperPartPercent;

        return new int[]{upperPartPercent, lowerPartPercent};
    }
}