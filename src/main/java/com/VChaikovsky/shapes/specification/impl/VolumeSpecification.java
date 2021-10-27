package com.vchaikovsky.shapes.specification.impl;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.specification.Specification;
import com.vchaikovsky.shapes.warehouse.PyramidsWarehouse;

public class VolumeSpecification implements Specification {
    private double minVolume;
    private double maxVolume;

    public VolumeSpecification(double minVolume, double maxVolume) {
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
    }

    @Override
    public boolean specify(Pyramid pyramid) {
        double pyramidVolume = PyramidsWarehouse
                .getInstance()
                .get(pyramid.getId())
                .pyramidVolume();

        return pyramidVolume >= minVolume && pyramidVolume <= maxVolume;
    }
}