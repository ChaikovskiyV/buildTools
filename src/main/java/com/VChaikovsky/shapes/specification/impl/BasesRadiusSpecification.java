package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;

public class BasesRadiusSpecification implements Specification {
    double minRadius;
    double maxRadius;

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