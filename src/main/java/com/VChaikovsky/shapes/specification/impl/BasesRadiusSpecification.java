package com.vchaikovsky.shapes.specification.impl;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.specification.Specification;

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