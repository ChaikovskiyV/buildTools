package com.vchaikovsky.shapes.service.impl;

import com.vchaikovsky.shapes.entity.impl.Point;
import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterCalculatorTest {
final static Logger logger = LogManager.getLogger();
private ParameterCalculator calculator;
private Point pointOne;
private Point pointTwo;
private Point pointThree;
private Pyramid sourcePyramid;
private Pyramid wrongPyramid;
private Pyramid pyramid;
double expectedVolume;
double expectedSurfaceSquare;
double expectedHeight;
private String volumeProportion;
private String noCrossing;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        calculator = ParameterCalculator.getInstance();
        int basesCountersNumber = 4;
        double radius = 20;
        pointOne = new Point(10, 20, 30);
        pointTwo = new Point(-10, 20, 30);
        pointThree = new Point(0, 20, 30);
        sourcePyramid = new Pyramid(pointOne, pointTwo, basesCountersNumber, radius);
        wrongPyramid = new Pyramid(pointOne, pointOne, basesCountersNumber, radius);
        pyramid = new Pyramid(pointThree, pointOne, basesCountersNumber, radius);
        expectedHeight = 20;
        expectedVolume = 5333.3333333333;
        expectedSurfaceSquare = 2185.6406460551;
        volumeProportion = "The " + sourcePyramid + " is divided by YZ plane as 12 : 88.";
        noCrossing = "The " + pyramid + " is not crossed by any basic plane.";
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void findVolume() throws ShapeException {
        double volume = BigDecimal.valueOf(calculator.findVolume(sourcePyramid))
                .setScale(10, RoundingMode.HALF_UP)
                .doubleValue();

        assertEquals(expectedVolume, volume);
    }

    @Test
    public void findSurfaceSquare() throws ShapeException {
        double surface = BigDecimal.valueOf(calculator.findSurfaceSquare(sourcePyramid))
                .setScale(10, RoundingMode.HALF_UP)
                .doubleValue();

        assertEquals(expectedSurfaceSquare, surface);
    }

    @Test
    public void findVolumeProportion() throws ShapeException {
        String result = calculator.findVolumeProportion(sourcePyramid);

        assertEquals(volumeProportion, result);
    }

    @Test
    public void ifNoCrossingByPlane() throws ShapeException {
        String result = calculator.findVolumeProportion(pyramid);

        assertEquals(noCrossing, result);
    }

    @Test
    public void isBasesOnBasePlane() throws ShapeException {
        Pyramid pyramid = new Pyramid(pointThree, pointOne, 6, 8);

        assertTrue(calculator.isBasesOnBasicPlane(pyramid));
    }
    
    @Test
    public void ifBasesNotONBasePlane() throws ShapeException {
        assertFalse(calculator.isBasesOnBasicPlane(sourcePyramid));
    }

    @Test
    public void findPyramidHeight() {
        double height = calculator.findPyramidHeight(sourcePyramid);

        assertEquals(expectedHeight, height);
    }

    @Test
    public void ifPyramidNotCorrect() {
        assertThrows(ShapeException.class, ()->calculator.findVolume(wrongPyramid));
    }

    @Test
    public void findDistanceToZeroPoint() {
        Point point = new Point(0, 3, 4);
        double expected = 5;
        double result = calculator.findDistanceToZeroPoint(point);

        assertEquals(expected, result);
    }
}