package com.VChaikovsky.shapes.filler;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;

public interface DataFillerInt {
    boolean addWareHouseAndRepository(Pyramid pyramid) throws ShapeException;
}
