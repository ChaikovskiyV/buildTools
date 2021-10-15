package com.VChaikovsky.shapes.creator.impl;

import com.VChaikovsky.shapes.creator.CreatorFactoryInt;
import com.VChaikovsky.shapes.entity.impl.Point;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.validator.impl.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class PyramidCreator implements CreatorFactoryInt {
    final static Logger logger = LogManager.getLogger();

    @Override
    public Pyramid createEntity(double[] dataArray) throws ShapeException {
        Pyramid pyramid = null;
        DataValidator validator = new DataValidator();
        PointCreator pointCreator = new PointCreator();

        double[] basesCenterData = Arrays.copyOf(dataArray, 3);
        double[] peakData = Arrays.copyOfRange(dataArray, 3, 6);
        int cornersNumber = (int) dataArray[6];
        double radius = dataArray[7];
        Point basesCenter = pointCreator.createEntity(basesCenterData);
        Point peak = pointCreator.createEntity(peakData);

        try {
            if(validator.isDiffPoints(basesCenter, peak) && validator.isValidCornersNumber(cornersNumber) && validator.isValidRadius(radius)) {
                pyramid = new Pyramid(basesCenter, peak, cornersNumber, radius);
            } else {
                throw new ShapeException("The array "+ Arrays.toString(dataArray)+" contains wrong data.");
            }
        } catch (NullPointerException e) {
            logger.error("The array "+ Arrays.toString(dataArray)+" contains wrong data.", e);
        }
        return pyramid;
    }
}