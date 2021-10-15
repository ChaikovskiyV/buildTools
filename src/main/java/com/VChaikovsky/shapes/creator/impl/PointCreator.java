package com.VChaikovsky.shapes.creator.impl;

import com.VChaikovsky.shapes.creator.CreatorFactoryInt;
import com.VChaikovsky.shapes.entity.impl.Point;

public class PointCreator implements CreatorFactoryInt {
    @Override
    public Point createEntity(double[] coordinates) {
        double x = coordinates[0];
        double y = coordinates[1];
        double z = coordinates[2];

        Point point = new Point(x, y, z);

        return point;
    }
}