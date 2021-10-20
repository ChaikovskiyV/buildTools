package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;

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