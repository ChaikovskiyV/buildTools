package com.vchaikovsky.shapes.specification.impl;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.specification.Specification;

public class CornersNumberSpecification implements Specification {
    private int minCornersNumber;
    private int maxCornersNumber;

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