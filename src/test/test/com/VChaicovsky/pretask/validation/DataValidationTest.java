package test.com.VChaicovsky.pretask.validation;

import com.VChaicovsky.pretask.validation.impl.DataValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataValidationTest {
    public static Logger logger = LogManager.getLogger();
    DataValidation dataValidation;
    String filename;
    String emptyFilename;
    String wrongFilename;
    String[] stringArray;
    String number;

    @BeforeAll
    void setUp() {
        dataValidation = new DataValidation();
        logger.info("Testing is starting...");
        filename = "sources/testFile.txt";
        emptyFilename = "sources/emptyFile.txt";
    }

    @AfterAll
    void tearDown() {
        logger.info("Tests have been finished.");
    }

    @Test
    public void fileIsEmpty() {
        assertTrue(dataValidation.fileIsEmpty(filename));
    }

    @Test
    public void ifFileEmpty() {
        assertFalse(dataValidation.fileIsEmpty(emptyFilename));
    }

    @Test
    public void checkQuantityNumbers() {
        stringArray = new String[] {"Hello", "everyone"};
        assertTrue(dataValidation.checkQuantityNumbers(stringArray));
    }

    @Test
    public void ifQuantityNumbersIsNotEnough(){
        stringArray = new String[] {"Hello"};
        assertFalse(dataValidation.checkQuantityNumbers(stringArray));
    }

    @Test
    public void validateData() {
        number = "-14.706";
        assertTrue(dataValidation.validateData(number));
    }

    @Test
    public void ifDataWrong() {
        number = "-14..706";
        assertFalse(dataValidation.validateData(number));
    }

    @Test
    public void fileFound() {
           assertTrue(dataValidation.fileFound(filename));
    }


    @Test
    public void IfFileNotFound() {
        wrongFilename = "_" + filename;
        assertFalse(dataValidation.fileFound(wrongFilename));
    }
}