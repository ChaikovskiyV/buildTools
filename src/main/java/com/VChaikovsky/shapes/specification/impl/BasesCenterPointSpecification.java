package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;

public class BasesCenterPointSpecification implements Specification {
    double zeroPointDistance;

    @Override
    public boolean specify(Pyramid pyramid) {
        return false;
    }
}
