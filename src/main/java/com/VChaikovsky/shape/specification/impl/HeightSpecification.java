package com.vchaikovsky.shape.specification.impl;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.specification.Specification;
import com.vchaikovsky.shape.warehouse.PyramidsWarehouse;

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