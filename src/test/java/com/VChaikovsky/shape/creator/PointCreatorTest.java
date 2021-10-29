package com.vchaikovsky.shape.creator;

import com.vchaikovsky.shape.creator.impl.PointCreator;
import com.vchaikovsky.shape.entity.impl.Point;
import com.vchaikovsky.shape.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PointCreatorTest {
    static final Logger logger = LogManager.getLogger();
    private Point expectedPoint;
    private PointCreator pointCreator;
    private Point resultPoint;
    private double[] dataArray;
    private double x;
    private double y;
    private double z;

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
    public void createEntityArray() throws ShapeException {
        resultPoint = pointCreator.createEntity(dataArray);

        assertEquals(expectedPoint, resultPoint);
    }

    @Test
    public void CreateEntityCoordinates() {
        resultPoint = pointCreator.createEntity(x, y, z);

        assertEquals(expectedPoint, resultPoint);
    }
}