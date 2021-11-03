package com.vchaikovsky.arrayclass.validation;

import com.vchaikovsky.arrayclass.validation.impl.DataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataValidationTest {
    static final Logger logger = LogManager.getLogger();
    private DataValidation validation;
    private Integer[] array;
    private String correctStr;
    private String wrongStr;

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
        boolean result = validation.validateArray(array);

        assertTrue(result);
    }

    @Test
    public void ifContainsNullElements(){
        array = new Integer[]{3, 5, null, 7};
        boolean result = validation.validateArray(array);

        assertFalse(result);
    }

    @Test
    public void isCorrectData(){
        boolean result = validation.isCorrectData(correctStr);

        assertTrue(result);
    }

    @Test
    public void ifStringIsWrong(){
        boolean result = validation.isCorrectData(wrongStr);

        assertFalse(result);
    }

    @Test
    public void ifStringIsEmpty(){
        String empty = "";
        boolean result =validation.isCorrectData(empty);

        assertFalse(result);
    }

    @Test
    public void isContainsNumbers(){
        correctStr = "//5//6//8";
        boolean result = validation.isIncludesNumbers(correctStr);

        assertTrue(result);
    }

    @Test
    public void ifContainsNoNumbers(){
        wrongStr = "//&//&//&";
        boolean result = validation.isIncludesNumbers(wrongStr);

        assertFalse(result);
    }
}