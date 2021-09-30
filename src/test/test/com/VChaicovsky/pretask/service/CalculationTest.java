package test.com.VChaicovsky.pretask.service;

import com.VChaicovsky.pretask.entity.CustomNumber;
import com.VChaicovsky.pretask.service.impl.Calculation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculationTest {
static final Logger logger = LogManager.getLogger();
double number;
CustomNumber numberOne;
CustomNumber numberTwo;
CustomNumber expectedResult;
Calculation calculation;

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
        expectedResult = new CustomNumber(numberOne.getNumber() - numberTwo.getNumber());
        Assertions.assertEquals(calculation.subtraction(numberOne, numberTwo).getNumber(), expectedResult.getNumber());
    }

    @org.junit.jupiter.api.Test
    public void testAddition() {
        expectedResult = new CustomNumber(numberTwo.getNumber() + numberOne.getNumber());
        Assertions.assertEquals(calculation.addition(numberTwo, numberOne).getNumber(), expectedResult.getNumber());
    }

    @org.junit.jupiter.api.Test
    public void testDivision(){
        expectedResult = new CustomNumber(numberOne.getNumber() / numberTwo.getNumber());
        Assertions.assertEquals(calculation.division(numberOne, numberTwo).getNumber(), expectedResult.getNumber());
    }

    @org.junit.jupiter.api.Test
    public void testMultiplication(){
        expectedResult = new CustomNumber(numberOne.getNumber() * numberTwo.getNumber());
        Assertions.assertEquals(calculation.multiplication(numberOne, numberTwo).getNumber(), expectedResult.getNumber());
    }
}