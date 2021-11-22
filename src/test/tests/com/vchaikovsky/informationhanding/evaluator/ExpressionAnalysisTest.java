package com.vchaikovsky.informationhanding.evaluator;

import com.vchaikovsky.informationhanding.entity.MathElement;
import com.vchaikovsky.informationhanding.entity.MathElementType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpressionAnalysisTest {
    static final Logger logger = LogManager.getLogger();
    private String expression;
    private ExpressionAnalysis expressionAnalysis;
    private List<MathElement> expected;
    private List<MathElement> result;
    private MathElement elementNumber;
    private MathElement elementSymbol;

    @BeforeAll
    void setUp() {
        logger.info("The testing is starting...");
        expression = "~2|(2<<2+22)";
        expressionAnalysis = new ExpressionAnalysis();
        expected = new ArrayList<>();
        elementSymbol = new MathElement(MathElementType.NOT, "~");
        expected.add(elementSymbol);
        elementNumber = new MathElement(MathElementType.NUMBER, "2");
        expected.add(elementNumber);
        elementSymbol = new MathElement(MathElementType.OR, "|");
        expected.add(elementSymbol);
        elementSymbol = new MathElement(MathElementType.LEFT_BRACKET, "(");
        expected.add(elementSymbol);
        expected.add(elementNumber);
        elementSymbol = new MathElement(MathElementType.LEFT_SHIFT, "<<");
        expected.add(elementSymbol);
        expected.add(elementNumber);
        elementSymbol = new MathElement(MathElementType.PLUS, "+");
        expected.add(elementSymbol);
        elementNumber = new MathElement(MathElementType.NUMBER, "22");
        expected.add(elementNumber);
        elementSymbol = new MathElement(MathElementType.RIGHT_BRACKET, ")");
        expected.add(elementSymbol);
        elementSymbol = new MathElement(MathElementType.EXPR_END, "");
        expected.add(elementSymbol);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void analyze() {
        result = expressionAnalysis.analyze(expression);

        assertEquals(expected, result);
    }
}