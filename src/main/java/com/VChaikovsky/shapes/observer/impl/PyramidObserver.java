package com.VChaikovsky.shapes.observer.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.event.PyramidEvent;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.observer.Observer;
import com.VChaikovsky.shapes.service.impl.ParameterCalculator;
import com.VChaikovsky.shapes.warehouse.PyramidParameters;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PyramidObserver implements Observer {
    final static Logger logger = LogManager.getLogger();
    private PyramidsWarehouse warehouse = PyramidsWarehouse.getInstance();
    private ParameterCalculator calculator = ParameterCalculator.getInstance();
    private Pyramid pyramid;
    private Long id;
    private PyramidParameters pyramidParameters;

    @Override
    public void parameterChanged(PyramidEvent event) throws ShapeException {
        pyramid = event.getPyramid();
        id = pyramid.getId();
        pyramidParameters = warehouse.get(id);
        pyramidParameters.setPyramidVolume(calculator.findVolume(pyramid));
        pyramidParameters.setBasesSide(calculator.findBasesSideLength(pyramid));
        pyramidParameters.setHeight(calculator.findPyramidHeight(pyramid));
        pyramidParameters.setSurfaceSquare(calculator.findSurfaceSquare(pyramid));
        pyramidParameters.setVolumeProportion(calculator.findVolumeProportion(pyramid));
        pyramidParameters.setIsBasesLaysOnBasicPlane(calculator.isBasesOnBasePlane(pyramid));
        warehouse.put(id, pyramidParameters);
    }
}