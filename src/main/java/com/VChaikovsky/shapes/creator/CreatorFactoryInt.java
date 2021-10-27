package com.vchaikovsky.shapes.creator;

import com.vchaikovsky.shapes.entity.GeometryEntity;
import com.vchaikovsky.shapes.exception.ShapeException;

public interface CreatorFactoryInt {
    GeometryEntity createEntity(double[] array) throws ShapeException;
}