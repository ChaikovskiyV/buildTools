package com.VChaikovsky.shapes.specification;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;

public interface Specification {
    boolean specify(Pyramid pyramid) throws ShapeException;
}
