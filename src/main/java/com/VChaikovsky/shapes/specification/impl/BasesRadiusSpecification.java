package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;

public class BasesRadiusSpecification implements Specification {
    double minRadius;
    double maxRadius;

    @Override
    public boolean specify(Pyramid pyramid) {
        return false;
    }
}
