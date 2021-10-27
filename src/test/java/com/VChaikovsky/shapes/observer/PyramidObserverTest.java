package com.vchaikovsky.shapes.observer;

import com.vchaikovsky.shapes.entity.impl.Point;
import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.entity.impl.PyramidParameters;
import com.vchaikovsky.shapes.exception.ShapeException;
import com.vchaikovsky.shapes.observer.impl.PyramidObserver;
import com.vchaikovsky.shapes.service.impl.ParameterCalculator;
import com.vchaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PyramidObserverTest {
    static final Logger logger = LogManager.getLogger();
    private PyramidsWarehouse warehouse;
    private ParameterCalculator calculator;
    private Pyramid pyramid;
    private Point basesCenter;
    private Point peak;
    private int cornersNumber;
    private double basesRadius;
    private PyramidParameters expectedParameters;
    private double height;
    private double side;
    private double volume;
    private double surfaceSquare;
    private boolean isPointOnPlane;
    private String volumeProportion;

    @BeforeAll
    void setUp() throws ShapeException {
        logger.info("Testing is starting ...");
        warehouse = PyramidsWarehouse.getInstance();
        calculator = ParameterCalculator.getInstance();
        basesCenter = new Point(5, 15, 45);
        peak = new Point(5, 15, -45);
        cornersNumber = 4;
        basesRadius = 25;
        pyramid = new Pyramid(basesCenter, peak, cornersNumber, basesRadius);
        pyramid.attach(new PyramidObserver());
        height = calculator.findPyramidHeight(pyramid);
        side = calculator.findBasesSideLength(pyramid);
        volume = calculator.findVolume(pyramid);
        surfaceSquare = calculator.findSurfaceSquare(pyramid);
        isPointOnPlane = calculator.isBasesOnBasicPlane(pyramid);
        volumeProportion = calculator.findVolumeProportion(pyramid);
        expectedParameters = new PyramidParameters(height, side, volume, surfaceSquare, isPointOnPlane, volumeProportion);
        warehouse.put(pyramid.getId(), expectedParameters);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void beforeParameterChanged() {
        PyramidParameters pyramidParameters = warehouse.get(pyramid.getId());

        assertEquals(expectedParameters, pyramidParameters);
    }

    @Test
    public void parametersChanged() {
        int newCornersNumber = 6;
        pyramid.setBasesCornersNumber(newCornersNumber);
        PyramidParameters pyramidParameters = warehouse.get(pyramid.getId());

        assertNotEquals(expectedParameters, pyramidParameters);
    }
}