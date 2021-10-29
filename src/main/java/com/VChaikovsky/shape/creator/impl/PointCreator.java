package com.vchaikovsky.shape.creator.impl;

import com.vchaikovsky.shape.creator.CreatorFactoryInt;
import com.vchaikovsky.shape.entity.impl.Point;
import com.vchaikovsky.shape.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class PointCreator implements CreatorFactoryInt {
    static final Logger logger = LogManager.getLogger();
    private static PointCreator instance;

    private PointCreator(){}

    public static PointCreator getInstance(){
        if(instance == null) {
            instance = new PointCreator();
        }
        return instance;
    }

    @Override
    public Point createEntity(double[] coordinates) throws ShapeException {
        int arrayLength = 3;

        if(coordinates == null || coordinates.length < arrayLength) {
            logger.error("Array " + Arrays.toString(coordinates) + " is not correct.");
            throw new ShapeException("Array " + Arrays.toString(coordinates) + " is not correct.");
        }
        double x = coordinates[0];
        double y = coordinates[1];
        double z = coordinates[2];

        return new Point(x, y, z);
    }

    public Point createEntity(double x, double y, double z) {
        return new Point(x, y, z);
    }
}