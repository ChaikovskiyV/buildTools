package com.vchaikovsky.shapes.creator.impl;

import com.vchaikovsky.shapes.creator.CreatorFactoryInt;
import com.vchaikovsky.shapes.entity.impl.Point;
import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.exception.ShapeException;
import com.vchaikovsky.shapes.observer.impl.PyramidObserver;
import com.vchaikovsky.shapes.validator.impl.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class PyramidCreator implements CreatorFactoryInt {
    static final Logger logger = LogManager.getLogger();
    private static PyramidCreator instance;

    private PyramidCreator(){}

    public static PyramidCreator getInstance(){
        if(instance == null) {
            instance = new PyramidCreator();
        }
        return instance;
    }

    @Override
    public Pyramid createEntity(double[] dataArray) throws ShapeException {
        int arrayLength = 8;
        if(dataArray.length < arrayLength) {
            throw new ShapeException("The array "+ Arrays.toString(dataArray)+" is not correct.");
        }
        Pyramid pyramid;
        DataValidator validator = DataValidator.getInstance();
        PointCreator pointCreator = PointCreator.getInstance();

        double[] basesCenterData = Arrays.copyOf(dataArray, 3);
        double[] peakData = Arrays.copyOfRange(dataArray, 3, 6);
        int cornersNumber = (int) dataArray[6];
        double radius = dataArray[7];
        Point basesCenter = pointCreator.createEntity(basesCenterData);
        Point peak = pointCreator.createEntity(peakData);

            if(!validator.isDiffPoints(basesCenter, peak) || !validator.isValidCornersNumber(cornersNumber) || !validator.isValidRadius(radius)) {
                throw new ShapeException("The array "+ Arrays.toString(dataArray)+" contains wrong data.");
            }
            pyramid = new Pyramid(basesCenter, peak, cornersNumber, radius);

            pyramid.attach(new PyramidObserver());

        return pyramid;
    }

    public Pyramid createEntity(double basesCenterX, double basesCenterY, double basesCenterZ, double peakX, double peakY, double peakZ, int cornersNumber, double basesCircumcircleRadius) {
        return new Pyramid(basesCenterX, basesCenterY, basesCenterZ, peakX, peakY, peakZ, cornersNumber, basesCircumcircleRadius);
    }
}