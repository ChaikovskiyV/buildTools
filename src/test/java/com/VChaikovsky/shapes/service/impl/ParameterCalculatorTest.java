package com.VChaikovsky.shapes.service.impl;

import com.VChaikovsky.shapes.entity.impl.Point;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterCalculatorTest {
final static Logger logger = LogManager.getLogger();
private ParameterCalculator calculator;
private Pyramid sourcePyramid;
private Pyramid wrongPyramid;
double expectedVolume;
double expectedSurfaceSquare;
double expectedHeight;


    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        calculator = new ParameterCalculator();
        Point pointOne = new Point(10, 20, 30);
        Point pointTwo = new Point(-5, 20, 30);
        sourcePyramid = new Pyramid(pointOne, pointTwo, 4, 20);
        wrongPyramid = new Pyramid(pointOne, pointOne, 4, 20);
        expectedHeight = 15;
        expectedVolume = 4000;
        expectedSurfaceSquare = 800 + 200 * Math.sqrt(34);

    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void findVolume() throws ShapeException {
        double volume = calculator.findVolume(sourcePyramid);

        assertEquals(expectedVolume, volume);
    }

    @Test
    public void findSurfaceSquare() throws ShapeException {
        double surface = calculator.findSurfaceSquare(sourcePyramid);

        assertEquals(expectedSurfaceSquare, surface);
    }

    @Test
    public void findVolumeProportion() {
    }

    @Test
    public void isBasesOnBasePlane() {
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
}