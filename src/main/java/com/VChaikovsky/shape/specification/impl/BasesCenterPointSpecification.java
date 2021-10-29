package com.vchaikovsky.shape.specification.impl;

import com.vchaikovsky.shape.entity.impl.Point;
import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.service.impl.ParameterCalculator;
import com.vchaikovsky.shape.specification.Specification;

public class BasesCenterPointSpecification implements Specification {
    private double minDistanceToZeroPoint;
    private double maxDistanceToZeroPoint;

    public BasesCenterPointSpecification(double minDistanceToZeroPoint, double maxDistanceToZeroPoint) {
        this.minDistanceToZeroPoint = minDistanceToZeroPoint;
        this.maxDistanceToZeroPoint = maxDistanceToZeroPoint;
    }

    @Override
    public boolean specify(Pyramid pyramid) {
        Point basesCenterPoint = pyramid.getBasesCenter();
        double distanceToZeroPoint = ParameterCalculator
                .getInstance()
                .findDistanceToZeroPoint(basesCenterPoint);

        return distanceToZeroPoint >= minDistanceToZeroPoint && distanceToZeroPoint <= maxDistanceToZeroPoint;
    }
}