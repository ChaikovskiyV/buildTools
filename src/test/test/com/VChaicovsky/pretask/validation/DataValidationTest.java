package test.com.VChaicovsky.pretask.validation;

import com.VChaicovsky.pretask.validation.impl.DataValidation;
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
    final static Logger logger = LogManager.getLogger();
    private DataValidation dataValidation;
    private String filename;
    private String emptyFilename;
    private String wrongFilename;
    private String[] stringArray;
    private String number;
    private boolean result;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting...");
        dataValidation = new DataValidation();
        filename = "sources/testFile.txt";
        emptyFilename = "sources/emptyFile.txt";
    }

    @AfterAll
    void tearDown() {
        logger.info("Tests have been finished.");
    }

    @Test
    public void fileIsEmpty() {
        result = dataValidation.fileIsEmpty(filename);

        assertTrue(result);
    }

    @Test
    public void ifFileEmpty() {
        result = dataValidation.fileIsEmpty(emptyFilename);

        assertFalse(result);
    }

    @Test
    public void checkQuantityNumbers() {
        stringArray = new String[] {"Hello", "everyone"};
        result = dataValidation.checkQuantityNumbers(stringArray);

        assertTrue(result);
    }

    @Test
    public void ifQuantityNumbersIsNotEnough(){
        stringArray = new String[] {"Hello"};
        result = dataValidation.checkQuantityNumbers(stringArray);

        assertFalse(result);
    }

    @Test
    public void validateData() {
        number = "-14.706";
        result = dataValidation.validateData(number);

        assertTrue(result);
    }

    @Test
    public void ifDataWrong() {
        number = "-14..706";
        result = dataValidation.validateData(number);

        assertFalse(result);
    }

    @Test
    public void fileFound() {
        result = dataValidation.fileFound(filename);

        assertTrue(result);
    }


    @Test
    public void IfFileNotFound() {
        wrongFilename = "_" + filename;
        result = dataValidation.fileFound(wrongFilename);

        assertFalse(result);
    }
}