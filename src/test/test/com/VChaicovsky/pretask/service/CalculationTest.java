package test.com.VChaicovsky.pretask.service;

import com.VChaicovsky.pretask.entity.CustomNumber;
import com.VChaicovsky.pretask.service.impl.Calculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculationTest {
    final static  Logger logger = LogManager.getLogger();
    private double number;
    private CustomNumber numberOne;
    private CustomNumber numberTwo;
    private Double expectedResult;
    private Double result;
    private Calculation calculation;


    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        calculation = new Calculation();
        number = 48.1;
        numberOne = new CustomNumber(number);
        number = 96.2;
        numberTwo = new CustomNumber(number);
    }

    @org.junit.jupiter.api.AfterAll
    void tearDown() {
        logger.info("Tests have been finished");
    }

    @org.junit.jupiter.api.Test
    public void testSubtraction() {
        expectedResult = numberOne.getNumber() - numberTwo.getNumber();
        result = calculation
                .subtraction(numberOne, numberTwo)
                .getNumber();

        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testAddition() {
        expectedResult = numberTwo.getNumber() + numberOne.getNumber();
        result = calculation
                .addition(numberTwo, numberOne)
                .getNumber();

        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testDivision(){
        expectedResult = numberOne.getNumber() / numberTwo.getNumber();
        result = calculation
                .division(numberOne, numberTwo)
                .getNumber();

        assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testMultiplication(){
        expectedResult = numberOne.getNumber() * numberTwo.getNumber();
        result = calculation
                .multiplication(numberOne, numberTwo)
                .getNumber();

        assertEquals(expectedResult, result);
    }
}