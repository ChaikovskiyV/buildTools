package validation;

import exception.WrongDataExeption;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataValidationTest {
    DataValidation dataValidation;
    boolean expectedResult;
    double expectedNumber;
    String string;
    String notCorectString;
    String wrongString;

    @BeforeAll
    void setUp(){
        System.out.println("Testing is starting...");
        dataValidation = new DataValidation();
        string = "8.6";
        notCorectString = "8..d6";
        wrongString = "wrongString";
        expectedNumber = 8.6;
    }

    @AfterAll
    void tearDown(){
        System.out.println("Tests have been finished.");
    }

    @Test
    void testCheckData() {
        dataValidation.setString(string);
        Assertions.assertTrue(dataValidation.checkData());
    }

    @Test
    void testCheckDataIfDataIsNotCorect(){
        dataValidation.setString(notCorectString);
        Assertions.assertFalse(dataValidation.checkData());
    }

    @Test
    void testCorectData() {
        Assertions.assertTrue(dataValidation.corectData(notCorectString));
    }

    @Test
    void testCorectDataIfDataIsWrong(){
        Assertions.assertFalse(dataValidation.corectData(wrongString));
    }

    @Test
    void testThrowExceptionIfDataisWrong(){

        Assertions.assertDoesNotThrow( ()->{
            dataValidation = new DataValidation(wrongString);
        });
    }

    @Test
    void testConversionStringToNumberIfDataIsCorect(){
        dataValidation = new DataValidation(string);
        Assertions.assertEquals(expectedNumber, dataValidation.getNumber());
    }

    @Test
    void testConversionStringToNumberIfDataIsNotCorect(){
        dataValidation = new DataValidation(notCorectString);
        Assertions.assertEquals(expectedNumber, dataValidation.getNumber());
    }
}