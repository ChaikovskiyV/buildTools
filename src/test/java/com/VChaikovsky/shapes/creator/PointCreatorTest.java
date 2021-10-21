package com.VChaikovsky.shapes.creator;

import com.VChaikovsky.shapes.creator.impl.PointCreator;
import com.VChaikovsky.shapes.entity.impl.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PointCreatorTest {
    static final Logger logger = LogManager.getLogger();
    Point expectedPoint;
    PointCreator pointCreator;
    Point resultPoint;
    double[] dataArray;
    double x;
    double y;
    double z;

    @BeforeAll
    void setUp() {
        logger.info("The testing is starting ...");
        pointCreator = PointCreator.getInstance();
        expectedPoint = new Point(5, -10, 120);
        dataArray = new double[] {5, -10, 120};
        x = 5;
        y = -10;
        z = 120;
    }

    @AfterEach
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    void createEntityArray() {
        resultPoint = pointCreator.createEntity(dataArray);

        assertEquals(expectedPoint, resultPoint);
    }

    @Test
    void CreateEntityCoordinates() {
        resultPoint = pointCreator.createEntity(x, y, z);

        assertEquals(expectedPoint, resultPoint);
    }
}