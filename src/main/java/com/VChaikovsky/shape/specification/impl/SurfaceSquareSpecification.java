package com.vchaikovsky.shape.specification.impl;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.specification.Specification;
import com.vchaikovsky.shape.warehouse.PyramidsWarehouse;

public class SurfaceSquareSpecification implements Specification {
    private double minSurfaceSquare;
    private double maxSurfaceSquare;

    public SurfaceSquareSpecification(double minSurfaceSquare, double maxSurfaceSquare) {
        this.minSurfaceSquare = minSurfaceSquare;
        this.maxSurfaceSquare = maxSurfaceSquare;
    }

    @Override
    public boolean specify(Pyramid pyramid) {
        double pyramidSurfaceSquare = PyramidsWarehouse
                .getInstance()
                .get(pyramid.getId())
                .surfaceSquare();

        return pyramidSurfaceSquare >= minSurfaceSquare && pyramidSurfaceSquare <= maxSurfaceSquare;
    }
}