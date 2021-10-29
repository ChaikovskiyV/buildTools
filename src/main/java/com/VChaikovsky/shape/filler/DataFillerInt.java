package com.vchaikovsky.shape.filler;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.exception.ShapeException;

public interface DataFillerInt {
    boolean addWareHouseAndRepository(Pyramid pyramid) throws ShapeException;
}
