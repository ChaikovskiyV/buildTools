package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculationTest {
double numberOne;
double numberTwo;
double expectedResult;
Calculation calculation;

    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        System.out.println("Testing is starting...");
        calculation = new Calculation();
        numberOne = 48.1;
        numberTwo = 96.2;
    }

    @org.junit.jupiter.api.AfterAll
    void tearDown() {
        System.out.println("Tests have been finished");
    }

    @org.junit.jupiter.api.Test
    void testSubtraction() {
        expectedResult = numberOne - numberTwo;
        Assertions.assertEquals(calculation.subtraction(numberOne, numberTwo), expectedResult);
    }

    @org.junit.jupiter.api.Test
    void testAddition() {
        expectedResult = numberTwo + numberOne;
        Assertions.assertEquals(calculation.addition(numberTwo, numberOne), expectedResult);
    }

    @org.junit.jupiter.api.Test
    void testDivision(){
        expectedResult = numberOne / numberTwo;
        Assertions.assertEquals(calculation.division(numberOne, numberTwo), expectedResult);
    }

    @org.junit.jupiter.api.Test
    void testMultiplication(){
        expectedResult = numberOne * numberTwo;
        Assertions.assertEquals(calculation.multiplication(numberOne, numberTwo), expectedResult);
    }
}