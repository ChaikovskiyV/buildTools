package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;

public class CornersNumberSpecification implements Specification {
    int minCornersNumber;
    int maxCornerNumber;

    @Override
    public boolean specify(Pyramid pyramid) {
        return false;
    }
}
