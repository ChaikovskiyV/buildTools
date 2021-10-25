package com.vchaikovsky.shapes.filler;

import com.vchaikovsky.shapes.entity.impl.Point;
import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.exception.ShapeException;
import com.vchaikovsky.shapes.filler.impl.DataFiller;
import com.vchaikovsky.shapes.repository.PyramidRepository;
import com.vchaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataFillerTest {
    static final Logger logger = LogManager.getLogger();
    private DataFiller dataFiller;
    private PyramidsWarehouse warehouse;
    private PyramidRepository repository;
    private Pyramid pyramid;
    private Point basesCenter;
    private Point peak;
    private int cornersNumber;
    private double basesRadius;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        dataFiller = DataFiller.getInstance();
        warehouse = PyramidsWarehouse.getInstance();
        repository = PyramidRepository.getInstance();
        basesCenter = new Point(10, 15, 20);
        peak = new Point(10, 15, -100);
        cornersNumber = 10;
        basesRadius = 50;
        pyramid = new Pyramid(basesCenter, peak, cornersNumber, basesRadius);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
   public void addWareHouseAndRepository() throws ShapeException {
        dataFiller.addWareHouseAndRepository(pyramid);
        boolean result = !warehouse.getParametersMap().isEmpty() && repository.size() != 0;

        assertTrue(result);
    }
}