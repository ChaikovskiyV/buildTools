package test.com.VChaicovsky.pretask.creater;

import com.VChaicovsky.pretask.creater.NumbersFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import com.VChaicovsky.pretask.entity.CustomNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumbersFactoryTest {
    Logger logger = LogManager.getLogger();
    String string;
    CustomNumber expectedCustomNumber;
    Double aDouble;

    @BeforeAll
    void setUp() {
        logger.info("Test is starting...");
        string = "-16.7";
        aDouble = Double.parseDouble(string);
        expectedCustomNumber = new CustomNumber(aDouble);
    }

    @AfterEach
    void tearDown() {
        logger.info("Test is finished");
    }

    @Test
    public void createNumber() {
        CustomNumber number = new NumbersFactory().createNumber(string);
        assertEquals(expectedCustomNumber.getNumber(), number.getNumber());
    }
}