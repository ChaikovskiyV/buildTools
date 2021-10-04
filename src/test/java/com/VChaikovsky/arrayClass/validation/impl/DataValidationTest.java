package com.VChaikovsky.arrayClass.validation.impl;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataValidationTest {
final static Logger logger = LogManager.getLogger();
DataValidation validation;
Integer[] array;
String correctStr;
String wrongStr;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        validation = new DataValidation();
        correctStr = "5/19/-6/8";
        wrongStr = "5/l9/-6/8";
    }

    @AfterAll
    void tearDown() {
        logger.info("Tests have been finished.");
    }

    @Test
    public void validateArray(){
        array = new Integer[] {-625, 896, 98, 0};
        assertTrue(validation.validateArray(array));
    }

    @Test
    public void ifIncludesNullElements(){
        array = new Integer[]{3, 5, null, 7};
        assertFalse(validation.validateArray(array));
    }

    @Test
    public void isCorrectData(){
        assertTrue(validation.isCorrectData(correctStr));
    }

    @Test
    public void ifStringIsWrong(){
        assertFalse(validation.isCorrectData(wrongStr));
    }

    @Test
    public void ifStringIsEmpty(){
        String empty = "";
        assertFalse(validation.isCorrectData(empty));
    }

    @Test
    public void isIncludesNumbers(){
        correctStr = "//5//6//8";
        assertTrue(validation.isIncludesNumbers(correctStr));
    }

    @Test
    public void ifIncludesNoNumbers(){
        wrongStr = "//&//&//&";
        assertFalse(validation.isIncludesNumbers(wrongStr));
    }
}