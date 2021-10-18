package com.VChaikovsky.shapes.filler.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.entity.impl.PyramidParameters;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.filler.WareHouseFillerInt;
import com.VChaikovsky.shapes.service.impl.ParameterCalculator;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WareHouseFiller implements WareHouseFillerInt {
    final static Logger logger = LogManager.getLogger();
    private static WareHouseFiller instance;
    private final PyramidsWarehouse warehouse = PyramidsWarehouse.getInstance();
    private final ParameterCalculator calculator = ParameterCalculator.getInstance();

    private WareHouseFiller() {}

    public static WareHouseFiller getInstance() {
        if(instance == null) {
            instance = new WareHouseFiller();
        }
        return instance;
    }

    @Override
    public void fillWareHouse(Pyramid pyramid) throws ShapeException {
        if(pyramid == null) {
            logger.error("The " + pyramid + " is null.");
            throw new ShapeException("The " + pyramid + " is null.");
        }
    long id = pyramid.getId();
    double height = calculator.findPyramidHeight(pyramid);
    double basesSide = calculator.findBasesSideLength(pyramid);
    double pyramidVolume = calculator.findVolume(pyramid);
    double surfaceSquare = calculator.findSurfaceSquare(pyramid);
    boolean isBasesLaysOnBasicPlane = calculator.isBasesOnBasicPlane(pyramid);
    String volumeProportion = calculator.findVolumeProportion(pyramid);

    PyramidParameters pyramidParameters = new PyramidParameters(height, basesSide, pyramidVolume, surfaceSquare, isBasesLaysOnBasicPlane, volumeProportion);

    warehouse.put(id, pyramidParameters);
    }
}