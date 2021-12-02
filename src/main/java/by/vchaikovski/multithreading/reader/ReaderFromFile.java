package by.vchaikovski.multithreading.reader;

import by.vchaikovski.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ReaderFromFile {
    static final Logger logger = LogManager.getLogger();
    private static ReaderFromFile instance;

    private ReaderFromFile() {
    }

    public static ReaderFromFile getInstance() {
        if (instance == null) {
            instance = new ReaderFromFile();
        }
        return instance;
    }

    public List<Integer> readeData(String filename) throws MultithreadingException {
        List<Integer> data;
        Path filepath = Path.of(filename);
        try (Stream<String> strings = Files.lines(filepath)) {
            data = strings.map(Integer::parseInt).toList();
        } catch (IOException e) {
            logger.error(() -> "The file " + filename + " was not found.", e);
            throw new MultithreadingException("The file " + filename + " was not found.", e);
        }
        return data;
    }
}