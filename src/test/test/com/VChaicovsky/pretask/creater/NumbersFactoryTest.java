package test.com.VChaicovsky.pretask.creater;

import com.VChaicovsky.pretask.creater.NumbersFactory;
import com.VChaicovsky.pretask.entity.CustomNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumbersFactoryTest {
    final static Logger logger = LogManager.getLogger();
    String string;
    CustomNumber customNumber;
    Double expectedResult;

    @BeforeAll
    void setUp() {
        logger.info("Test is starting...");
        string = "-16.7";
        expectedResult = -16.7;
        customNumber = new NumbersFactory().createNumber(string);
    }

    @AfterAll
    void tearDown() {
        logger.info("Test is finished");
    }

    @Test
    public void createNumber() {
        double result = customNumber.getNumber();

        assertEquals(expectedResult, result);
    }
}