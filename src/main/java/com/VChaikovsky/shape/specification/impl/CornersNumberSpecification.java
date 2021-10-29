package com.vchaikovsky.shape.specification.impl;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.specification.Specification;

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