package com.VChaikovsky.shapes.warehouse;

import com.VChaikovsky.shapes.service.impl.ParameterCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PyramidParameters {
    final static Logger logger = LogManager.getLogger();
    private ParameterCalculator calculator = ParameterCalculator.getInstance();
    private double height;
    private double basesSide;
    private double pyramidVolume;
    private double surfaceSquare;
    private boolean isBasesLaysOnBasicPlane;
    private String volumeProportion;

    public PyramidParameters(double height, double basesSide, double pyramidVolume, double surfaceSquare, boolean isBasesLaysOnBasicPlane, String volumeProportion) {
        this.height = height;
        this.basesSide = basesSide;
        this.pyramidVolume = pyramidVolume;
        this.surfaceSquare = surfaceSquare;
        this.isBasesLaysOnBasicPlane = isBasesLaysOnBasicPlane;
        this.volumeProportion = volumeProportion;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBasesSide() {
        return basesSide;
    }

    public void setBasesSide(double basesSide) {
        this.basesSide = basesSide;
    }

    public double getPyramidVolume() {
        return pyramidVolume;
    }

    public void setPyramidVolume(double pyramidVolume) {
        this.pyramidVolume = pyramidVolume;
    }

    public double getSurfaceSquare() {
        return surfaceSquare;
    }

    public void setSurfaceSquare(double surfaceSquare) {
        this.surfaceSquare = surfaceSquare;
    }

    public boolean isBasesLaysOnBasicPlane() {
        return isBasesLaysOnBasicPlane;
    }

    public void setIsBasesLaysOnBasicPlane(boolean basesLaysOnBasicPlane) {
        isBasesLaysOnBasicPlane = basesLaysOnBasicPlane;
    }

    public String getVolumeProportion() {
        return volumeProportion;
    }

    public void setVolumeProportion(String volumeProportion) {
        this.volumeProportion = volumeProportion;
    }
}