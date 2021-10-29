package com.vchaikovsky.shape.specification.impl;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.specification.Specification;

public class BasesRadiusSpecification implements Specification {
    private double minRadius;
    private double maxRadius;

    public BasesRadiusSpecification(double minRadius, double maxRadius) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
    }

    @Override
    public boolean specify(Pyramid pyramid) {
        double radius = pyramid.getCircumcircleRadius();

        return radius >= minRadius && radius <= maxRadius;
    }
}