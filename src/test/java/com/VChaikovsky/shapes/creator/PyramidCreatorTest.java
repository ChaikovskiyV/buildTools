package com.vchaikovsky.shapes.creator;

import com.vchaikovsky.shapes.creator.impl.PyramidCreator;
import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PyramidCreatorTest {
    static final Logger logger = LogManager.getLogger();
    PyramidCreator pyramidCreator;
    double [] dataArray;
    double basesCenterX;
    double basesCenterY;
    double basesCenterZ;
    double peakX;
    double peakY;
    double peakZ;
    int cornersNumber;
    double basesCircumcircleRadius;
    Pyramid expectedPyramid;
    Pyramid resultPyramid;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        pyramidCreator = PyramidCreator.getInstance();
        dataArray = new double[] {1, 2, 3, 101, 2, 3, 6, 50};
        basesCenterX = 1;
        basesCenterY = 2;
        basesCenterZ = 3;
        peakX = 101;
        peakY = 2;
        peakZ = 3;
        cornersNumber = 6;
        basesCircumcircleRadius = 50;
        expectedPyramid = new Pyramid(basesCenterX, basesCenterY, basesCenterZ, peakX, peakY, peakZ, cornersNumber, basesCircumcircleRadius);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished ...");
    }

    @Test
    public void createEntity() throws ShapeException {
        resultPyramid = pyramidCreator.createEntity(dataArray);

        assertEquals(expectedPyramid, resultPyramid);
    }

    @Test
    public void testCreateEntity() {
        resultPyramid = pyramidCreator.createEntity(basesCenterX, basesCenterY, basesCenterZ, peakX, peakY, peakZ, cornersNumber, basesCircumcircleRadius);

        assertEquals(expectedPyramid, resultPyramid);
    }

    @Test
    public void ifArrayNotCorrect() {
        double[] dataArray = new double[] {4, 8, 12, 4, 6, 12};

        assertThrows(ShapeException.class, ()-> pyramidCreator.createEntity(dataArray));
    }

    @Test
    public void ifArrayContainsNotCorrectRadius() {
        double[] dataArray = new double[] {4, 5, 7, 14, 5, 7, 4, -8};

        assertThrows(ShapeException.class, ()-> pyramidCreator.createEntity(dataArray));
    }

    @Test
    public void ifArrayContainsNotCorrectCornersNumber() {
        double[] dataArray = new double[] {4, 5, 7, 14, 5, 7, 2, 8};

        assertThrows(ShapeException.class, ()-> pyramidCreator.createEntity(dataArray));
    }

    @Test
    public void ifArrayContainsTheSamePoints() {
        double[] dataArray = new double[] {4, 5, 7, 4, 5, 7, 4, 8};

        assertThrows(ShapeException.class, ()-> pyramidCreator.createEntity(dataArray));
    }
}