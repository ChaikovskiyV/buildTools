package com.vchaikovsky.shape.filler.impl;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.entity.impl.PyramidParameters;
import com.vchaikovsky.shape.exception.ShapeException;
import com.vchaikovsky.shape.filler.DataFillerInt;
import com.vchaikovsky.shape.repository.PyramidRepository;
import com.vchaikovsky.shape.service.impl.ParameterCalculator;
import com.vchaikovsky.shape.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataFiller implements DataFillerInt {
    static final Logger logger = LogManager.getLogger();
    private static DataFiller instance;

    private DataFiller() {}

    public static DataFiller getInstance() {
        if(instance == null) {
            instance = new DataFiller();
        }
        return instance;
    }

    @Override
    public boolean addWareHouseAndRepository(Pyramid pyramid) throws ShapeException {
        return addRepository(pyramid) && addWareHouse(pyramid);
    }

    private boolean addWareHouse(Pyramid pyramid) throws ShapeException {
        ParameterCalculator calculator = ParameterCalculator.getInstance();
        boolean result = true;

        if(pyramid != null) {
            long id = pyramid.getId();
            double height = calculator.findPyramidHeight(pyramid);
            double basesSide = calculator.findBasesSideLength(pyramid);
            double pyramidVolume = calculator.findVolume(pyramid);
            double surfaceSquare = calculator.findSurfaceSquare(pyramid);
            boolean isBasesLaysOnBasicPlane = calculator.isBasesOnBasicPlane(pyramid);
            String volumeProportion = calculator.findVolumeProportion(pyramid);

            PyramidParameters pyramidParameters = new PyramidParameters(height, basesSide, pyramidVolume, surfaceSquare, isBasesLaysOnBasicPlane, volumeProportion);

            PyramidsWarehouse
                    .getInstance()
                    .put(id, pyramidParameters);
        } else {
            logger.warn("The " + pyramid + " is null.");
            result = false;
        }

        return result;
    }

    private boolean addRepository(Pyramid pyramid) {
        boolean result = true;

        if(pyramid != null) {
            PyramidRepository
                    .getInstance()
                    .add(pyramid);
        } else {
            result = false;
        }

        return result;
    }
}