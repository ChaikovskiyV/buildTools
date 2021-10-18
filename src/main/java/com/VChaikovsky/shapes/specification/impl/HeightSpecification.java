package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.specification.Specification;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;

public class HeightSpecification implements Specification {
    double minPyramidHeight;
    double maxPyramidHeight;

    public HeightSpecification(double minPyramidHeight, double maxPyramidHeight) {
        this.minPyramidHeight = minPyramidHeight;
        this.maxPyramidHeight = maxPyramidHeight;
    }

    @Override
    public boolean specify(Pyramid pyramid) throws ShapeException {
        double pyramidHeight = PyramidsWarehouse
                .getInstance()
                .get(pyramid.getId())
                .height();
        return pyramidHeight >= minPyramidHeight && pyramidHeight <= maxPyramidHeight;
    }
}