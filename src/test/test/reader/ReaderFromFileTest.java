package reader;

import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReaderFromFileTest {
    String[] expectedArray;
    String filename;
    Path filePath;

    @BeforeAll
    void setUp() {
        System.out.println("Testing is starting ...");
        expectedArray = new String[]{"hello", "everyone"};
        filename = "testFile.txt";
        filePath = Path.of(filename);

       try {
           Files.write(filePath, List.<CharSequence>of(expectedArray));
    } catch (IOException e){
            e.printStackTrace();
        }
    }

    @AfterAll
    void tearDown() {
        System.out.println("The test has been finished.");
    }

    @Test
    void readArrayFromFile() {
        String[] strings = null;
        try {
            List<String> list = Files.readAllLines(filePath);
            strings = list.toArray(String[]::new);
        } catch (IOException e){
            e.printStackTrace();
        }
        Assertions.assertArrayEquals(expectedArray, strings);
    }
}