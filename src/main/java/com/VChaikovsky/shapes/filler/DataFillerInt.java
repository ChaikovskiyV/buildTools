package com.vchaikovsky.shapes.filler;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.exception.ShapeException;

public interface DataFillerInt {
    boolean addWareHouseAndRepository(Pyramid pyramid) throws ShapeException;
}
