package com.vchaikovsky.shape.service;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.exception.ShapeException;

public interface ParametersCalculatorInt {

    double findVolume(Pyramid pyramid) throws ShapeException;

    double findSurfaceSquare(Pyramid pyramid) throws ShapeException;

    String findVolumeProportion(Pyramid pyramid) throws ShapeException;

    boolean isBasesOnBasicPlane(Pyramid pyramid) throws ShapeException;
}