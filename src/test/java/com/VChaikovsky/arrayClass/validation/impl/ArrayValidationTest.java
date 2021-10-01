package com.VChaikovsky.arrayClass.validation.impl;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArrayValidationTest {
final static Logger logger = LogManager.getLogger();
ArrayValidation validation;
Double[] array;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        validation = new ArrayValidation();
    }

    @AfterAll
    void tearDown() {
        logger.info("Tests have been finished.");
    }

    @Test
    public void validateArray() throws WrongDataException {
        array = new Double[] {-6.25, 8.96, null, 0.0};
        assertTrue(validation.validateArray(array));
    }

    @Test
    public void ifArrayNull(){
        array = null;
        assertThrows(WrongDataException.class, ()-> validation.validateArray(array));
    }
}