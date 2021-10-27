package com.vchaikovsky.shapes.service;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.exception.ShapeException;

public interface ParametersCalculatorInt {

    double findVolume(Pyramid pyramid) throws ShapeException;

    double findSurfaceSquare(Pyramid pyramid) throws ShapeException;

    String findVolumeProportion(Pyramid pyramid) throws ShapeException;

    boolean isBasesOnBasicPlane(Pyramid pyramid) throws ShapeException;
}
