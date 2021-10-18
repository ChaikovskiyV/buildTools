package com.VChaikovsky.shapes.service;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;

public interface ParametersCalculatorInt {

    double findVolume(Pyramid pyramid) throws ShapeException;

    double findSurfaceSquare(Pyramid pyramid) throws ShapeException;

    String findVolumeProportion(Pyramid pyramid) throws ShapeException;

    boolean isBasesOnBasicPlane(Pyramid pyramid) throws ShapeException;
}
