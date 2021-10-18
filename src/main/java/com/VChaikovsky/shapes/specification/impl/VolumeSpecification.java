package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.specification.Specification;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;

public class VolumeSpecification implements Specification {
    double minVolume;
    double maxVolume;

    public VolumeSpecification(double minVolume, double maxVolume) {
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
    }

    @Override
    public boolean specify(Pyramid pyramid) throws ShapeException {
        double pyramidVolume = PyramidsWarehouse
                .getInstance()
                .get(pyramid.getId())
                .pyramidVolume();

        return pyramidVolume >= minVolume && pyramidVolume <= maxVolume;
    }
}