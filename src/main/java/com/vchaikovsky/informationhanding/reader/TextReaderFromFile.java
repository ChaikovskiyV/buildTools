package com.vchaikovsky.informationhanding.reader;

import com.vchaikovsky.informationhanding.exception.HandingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReaderFromFile {
    static  final Logger logger = LogManager.getLogger();
    static TextReaderFromFile instance;

    private TextReaderFromFile() {
    }

    public static TextReaderFromFile getInstance() {
        if(instance == null) {
            instance = new TextReaderFromFile();
        }
        return instance;
    }

    public String readText(String filename) throws HandingException {
        StringBuilder text = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
            reader.lines().forEach(s -> text.append(s).append("\n"));
        } catch (IOException e) {
            logger.error("File " + filename + " was not found.", e);
            throw new HandingException("File " + filename + " was not found.", e);
        }
        return text.toString();
    }
}