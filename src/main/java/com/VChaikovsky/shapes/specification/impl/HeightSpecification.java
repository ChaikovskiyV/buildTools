package com.vchaikovsky.shapes.specification.impl;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.specification.Specification;
import com.vchaikovsky.shapes.warehouse.PyramidsWarehouse;

public class HeightSpecification implements Specification {
    private double minPyramidHeight;
    private double maxPyramidHeight;

    public HeightSpecification(double minPyramidHeight, double maxPyramidHeight) {
        this.minPyramidHeight = minPyramidHeight;
        this.maxPyramidHeight = maxPyramidHeight;
    }

    @Override
    public boolean specify(Pyramid pyramid) {
        double pyramidHeight = PyramidsWarehouse
                .getInstance()
                .get(pyramid.getId())
                .height();
        return pyramidHeight >= minPyramidHeight && pyramidHeight <= maxPyramidHeight;
    }
}