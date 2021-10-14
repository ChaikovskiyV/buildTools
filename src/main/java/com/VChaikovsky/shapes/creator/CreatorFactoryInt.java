package com.VChaikovsky.shapes.creator;

import com.VChaikovsky.shapes.entity.GeometryEntity;
import com.VChaikovsky.shapes.exception.ShapeException;

public interface CreatorFactoryInt {

    GeometryEntity createEntity(double[] array) throws ShapeException;

    long generateId();
}