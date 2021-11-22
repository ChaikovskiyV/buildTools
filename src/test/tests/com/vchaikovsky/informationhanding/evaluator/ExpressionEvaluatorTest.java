package com.vchaikovsky.informationhanding.evaluator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpressionEvaluatorTest {
    static final Logger logger = LogManager.getLogger();
    private ExpressionEvaluator arithmeticEvaluator;
    private ExpressionEvaluator binaryEvaluator;
    private String arithmeticExpression;
    private String binaryExpression;
    private int result;
    private int expected;

    @BeforeAll
    void setUp() {
        logger.info("The testing is starting...");
        arithmeticExpression = "(2+2*2)/3";
        arithmeticEvaluator = new ExpressionEvaluator(arithmeticExpression);
        binaryExpression = "1^(5&2|2)";
        binaryEvaluator = new ExpressionEvaluator(binaryExpression);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void arithmeticEvaluate() {
        result = arithmeticEvaluator.evaluate();
        expected = 2;

        assertEquals(expected, result);
    }

    @Test
    public void binaryEvaluate() {
        result = binaryEvaluator.evaluate();
        expected = 3;

        assertEquals(expected, result);
    }
}