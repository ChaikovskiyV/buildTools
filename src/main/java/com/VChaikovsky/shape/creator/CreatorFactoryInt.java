package com.vchaikovsky.shape.creator;

import com.vchaikovsky.shape.entity.GeometryEntity;
import com.vchaikovsky.shape.exception.ShapeException;

public interface CreatorFactoryInt {
    GeometryEntity createEntity(double[] array) throws ShapeException;
}