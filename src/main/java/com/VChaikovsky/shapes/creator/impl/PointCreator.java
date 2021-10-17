package com.VChaikovsky.shapes.creator.impl;

import com.VChaikovsky.shapes.creator.CreatorFactoryInt;
import com.VChaikovsky.shapes.entity.impl.Point;

public class PointCreator implements CreatorFactoryInt {
    private static PointCreator instance;

    private PointCreator(){}

    public static PointCreator getInstance(){
        if(instance == null) {
            instance = new PointCreator();
        }
        return instance;
    }
    @Override
    public Point createEntity(double[] coordinates) {
        double x = coordinates[0];
        double y = coordinates[1];
        double z = coordinates[2];

        return new Point(x, y, z);
    }

    public Point createEntity(double x, double y, double z) {
        return new Point(x, y, z);
    }
}