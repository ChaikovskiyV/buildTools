package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;

public class CornersNumberSpecification implements Specification {
    int minCornersNumber;
    int maxCornersNumber;

    public CornersNumberSpecification(int minCornersNumber, int maxCornerNumber) {
        this.minCornersNumber = minCornersNumber;
        this.maxCornersNumber = maxCornerNumber;
    }

    @Override
    public boolean specify(Pyramid pyramid) {
        int cornersNumber = pyramid.getBasesCornersNumber();

        return cornersNumber >= minCornersNumber && cornersNumber <= maxCornersNumber;
    }
}