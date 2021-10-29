package com.vchaikovsky.shape.entity.impl;

public record PyramidParameters (double height, double basesSide, double pyramidVolume,
                                 double surfaceSquare, boolean isBasesLaysOnBasicPlane,
                                 String volumeProportion) {
}