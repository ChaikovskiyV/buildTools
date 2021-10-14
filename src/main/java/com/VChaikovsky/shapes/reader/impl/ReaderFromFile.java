package com.VChaikovsky.shapes.reader.impl;

import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.reader.ReaderFromFileInt;
import com.VChaikovsky.shapes.validator.impl.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReaderFromFile implements ReaderFromFileInt {
    final static Logger logger = LogManager.getLogger();

    @Override
    public List<String> readData(String filepath) throws ShapeException {
        DataValidator validator = new DataValidator();
        List<String> strings;
        try {
            strings = Files
                    .lines(Path.of(filepath))
                    .filter(s-> validator.isValidString(s))
                    .toList();
        } catch (IOException e) {
            logger.error("File "+filepath+" was not found.");
            throw new ShapeException("File "+filepath+" was not found.", e);
        }
        return strings;
    }
}
