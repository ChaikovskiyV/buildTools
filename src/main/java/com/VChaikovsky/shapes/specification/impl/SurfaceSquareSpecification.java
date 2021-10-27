package com.vchaikovsky.shapes.specification.impl;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.specification.Specification;
import com.vchaikovsky.shapes.warehouse.PyramidsWarehouse;

public class SurfaceSquareSpecification implements Specification {
    double minSurfaceSquare;
    double maxSurfaceSquare;

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