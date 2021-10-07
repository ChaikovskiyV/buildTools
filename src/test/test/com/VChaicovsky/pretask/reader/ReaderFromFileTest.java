package test.com.VChaicovsky.pretask.reader;


import com.VChaicovsky.pretask.exception.WrongDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReaderFromFileTest {
    final static Logger logger = LogManager.getLogger();
    String[] expectedArray;
    String filename;
    Path filePath;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        expectedArray = new String[]{"hello", "everyone"};
        filename = "sources/testFile.txt";
        filePath = Path.of(filename);

       try {
           Files.write(filePath, List.<CharSequence>of(expectedArray));
    } catch (IOException e){
            logger.log(Level.ERROR, new WrongDataException("Such file was not found", e));
        }
    }

    @AfterAll
    void tearDown() {
        logger.info("The test has been finished.");
    }

    @Test
    public void readArrayFromFile() {
        String[] strings = null;
        try {
            List<String> list = Files.readAllLines(filePath);
            strings = list.toArray(String[]::new);
        } catch (IOException e){
            logger.log(Level.ERROR, new WrongDataException("Such file was not found", e));
        }
        assertArrayEquals(expectedArray, strings);
    }
}