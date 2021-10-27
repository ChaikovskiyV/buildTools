package com.vchaikovsky.shapes.entity.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record PyramidParameters (double height, double basesSide, double pyramidVolume, double surfaceSquare, boolean isBasesLaysOnBasicPlane, String volumeProportion) {
    final static Logger logger = LogManager.getLogger();
}