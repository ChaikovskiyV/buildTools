package com.vchaikovsky.shapes.validator.impl;

import com.vchaikovsky.shapes.entity.impl.Point;
import com.vchaikovsky.shapes.entity.impl.Pyramid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataValidatorTest {
final static Logger logger = LogManager.getLogger();
private DataValidator validator = DataValidator.getInstance();
private String sourceString;
private String wrongString;
private String[] sourceArray;
private String[] wrongArray;
private String stringNumber;
private Point pointOne;
private Point pointTwo;
private Point pointThree;
private Pyramid sourcePyramid;
private Pyramid wrongPyramid;
private int basesCornersNumber;
private double radius;

    @BeforeAll
    void setUp() {
        final String DELIMITER_REGEX = "\\s";
        logger.info("Testing is starting ...");
        sourceString = "-6 15 94 16 15 96 8 20";
        wrongString = "1O 18 90 10 -18 90 3 15";
        sourceArray = sourceString.split(DELIMITER_REGEX);
        wrongArray = wrongString.split(DELIMITER_REGEX);
        pointOne = new Point(1, 10, 100);
        pointTwo = new Point(1, 10, 150);
        pointThree = new Point(1, 10, 100);
        basesCornersNumber = 6;
        radius = 25;
        sourcePyramid = new Pyramid(pointOne, pointTwo, basesCornersNumber, radius);
        wrongPyramid = new Pyramid(pointOne, pointThree, basesCornersNumber, radius);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void isValidString() {
        assertTrue(validator.isValidString(sourceString));
    }

    @Test
    public void ifStringIsWrong() {
        assertFalse(validator.isValidString(wrongString));
    }

    @Test
    public void isValidParam() {
        assertTrue(validator.isValidParam(sourceArray));
    }

    @Test
    public void ifArrayContainsWrongSymbols() {
        assertFalse(validator.isValidParam(wrongArray));
    }

    @Test
    public void ifArrayShorterItNeed() {
        wrongArray = new String[] {"5", "4", "7", "9", "12"};

        assertFalse(validator.isValidParam(wrongArray));
    }

    @Test
    public void isValidStringNumber() {
        stringNumber = "-5.165";

        assertTrue(validator.isValidStringNumber(stringNumber));
    }

    @Test
    public void ifStringNumberNotCorrect() {
        stringNumber = "-5..165";

        assertFalse(validator.isValidStringNumber(stringNumber));
    }

    @Test
    public void isDiffPoints() {
        assertTrue(validator.isDiffPoints(pointOne, pointTwo));
    }

    @Test
    public void ifPointsTheSame() {
        assertFalse(validator.isDiffPoints(pointOne, pointThree));
    }

    @Test
    public void isValidPyramid() {
        assertTrue(validator.isValidPyramid(sourcePyramid));
    }

    @Test
    public void ifPyramidNotCorrect() {
        assertFalse(validator.isValidPyramid(wrongPyramid));
    }

    @Test
    public void ifRadiusNotCorrect() {
        wrongPyramid = new Pyramid(pointOne, pointTwo, basesCornersNumber, 0);

        assertFalse(validator.isValidPyramid(wrongPyramid));
    }

    @Test
    public void ifCornersNumberNotCorrect() {
        wrongPyramid = new Pyramid(pointOne, pointTwo, 0, radius);

        assertFalse(validator.isValidPyramid(wrongPyramid));
    }

    @Test
    public void isParallelAxis() {
        assertTrue(validator.isParallelAxis(sourcePyramid));
    }

    @Test
    public void ifPyramidHighNotParallelAxis() {
        Point newPoint = new Point(0, 10, 150);
        sourcePyramid.setPeak(newPoint);

        assertFalse(validator.isParallelAxis(sourcePyramid));
    }
}