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
        if (validator.isValidPyramid(pyramid)){
            double high = findPyramidHeight(pyramid);
            double sideLength = findBasesSideLength(pyramid);
            int cornersNumber = pyramid.getBasesCornersNumber();

            volume = cornersNumber * Math.pow(sideLength, 2) * high / (12 * Math.abs(Math.tan(Math.PI / cornersNumber)));
        } else {
            logger.error("The " + pyramid+" is not pyramid.");
            throw new ShapeException("The " + pyramid+" is not pyramid.");
        }
        return volume;
    }

    @Override
    public double findSurfaceSquare(Pyramid pyramid) throws ShapeException {
        double square;
        if(validator.isValidPyramid(pyramid)){
            double high = findPyramidHeight(pyramid);
            double radius = pyramid.getCircumcircleRadius();
            int cornersNumber = pyramid.getBasesCornersNumber();

            square = cornersNumber * (Math.pow(radius, 2) * Math.abs(Math.sin(2 * Math.PI / cornersNumber)) + radius * Math.abs(Math.sin(Math.PI / cornersNumber)) *
                    Math.sqrt(Math.pow(high, 2) + Math.pow(radius * Math.abs(Math.sin(Math.PI * (cornersNumber - 2) / cornersNumber)), 2)));
        } else {
            logger.error("The " + pyramid+" is not pyramid.");
            throw new ShapeException("The " + pyramid+" is not pyramid.");
        }
        return square;
    }

    @Override
    public String findVolumeProportion(Pyramid pyramid) throws ShapeException {
        String result = "The " + pyramid.toString() + " is not crossed by any plane";

        if(validator.isValidPyramid(pyramid)){
            Point basesCenter = pyramid.getBasesCenter();
            Point peak = pyramid.getPeak();

            if(!isBasesOnBasePlane(pyramid) && !isPointOnBasicPlane(peak)){
                Point planePoint;
                int[] volumeProportion;

                if(findParallelAxis(pyramid).equals("X") && (basesCenter.getX() / peak.getX()) < 0){
                    planePoint = new Point(0, basesCenter.getY(), basesCenter.getZ());
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + "is divided by YZ plane as " + volumeProportion[0] +
                            " to " + volumeProportion[1] + ".";
                }
                else if(findParallelAxis(pyramid).equals("Y") && (basesCenter.getY() / peak.getY()) < 0){
                    planePoint = new Point(basesCenter.getX(), 0, basesCenter.getZ());
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + "is divided by XZ plane as " + volumeProportion[0] +
                            " to " + volumeProportion[1] + ".";
                }
                else if(findParallelAxis(pyramid).equals("Z") && (basesCenter.getZ() / peak.getZ()) < 0){
                    planePoint = new Point(basesCenter.getX(), basesCenter.getY(), 0);
                    volumeProportion = calculateVolumeProportional(pyramid, planePoint);
                    result = "The " + pyramid + "is divided by XY plane as " + volumeProportion[0] +
                            " to " + volumeProportion[1] + ".";
                }
            }
        } else {
            logger.error("The " + pyramid+" is not pyramid.");
            throw new ShapeException("The " + pyramid+" is not pyramid.");
        }
        return result;
    }

    @Override
    public boolean isBasesOnBasePlane(Pyramid pyramid) throws ShapeException {
        boolean result = false;
        /*String result = new StringBuilder("The bases of the ")        //if message will be necessary
                .append(pyramid)
                .append(" doesn't lay on any basic plane.")
                .toString();*/
        if(validator.isValidPyramid(pyramid)){
            Point basesCenter = pyramid.getBasesCenter();
            if(isPointOnBasicPlane(basesCenter)){
                result = true;
                /*StringBuilder builder = new StringBuilder("The bases of the ")
                        .append(pyramid)
                        .append(" lays on the plane ");                 //if message will be necessary
                if(basesCenter.getX() == 0){
                    result = builder
                            .append("YZ.")
                            .toString();
                } else if(basesCenter.getY() == 0){
                    result = builder
                            .append("XZ.")
                            .toString();
                } else {
                    result = builder
                            .append("XY.")
                            .toString();
                }*/
            }
        } else {
            logger.error("The " + pyramid +" is not pyramid.");
            throw new ShapeException("The " + pyramid +" is not pyramid.");
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

        double sideLength = 2 * radius / Math.abs(Math.sin(Math.PI / cornersNumber));

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
        double newHigh = Math.sqrt(Math.pow(pyramid.getBasesCenter().getX() - newCenterPoint.getX(), 2) +
                Math.pow(pyramid.getBasesCenter().getY() - newCenterPoint.getY(), 2) +
                Math.pow(pyramid.getBasesCenter().getZ() - newCenterPoint.getZ(), 2));
        double newRadius = newHigh * pyramid.getCircumcircleRadius() / findPyramidHeight(pyramid);

        Pyramid newPyramid = new Pyramid(newCenterPoint, pyramid.getPeak(), pyramid.getBasesCornersNumber(), newRadius);

        int upperPartPercent = (int) (100 * findVolume(newPyramid) / findVolume(pyramid));
        int lowerPartPercent = 100 - upperPartPercent;

        return new int[]{upperPartPercent, lowerPartPercent};
    }
}