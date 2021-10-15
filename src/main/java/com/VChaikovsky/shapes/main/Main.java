package com.VChaikovsky.shapes.main;

import com.VChaikovsky.shapes.creator.impl.PyramidCreator;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.parser.impl.PyramidParameterParser;
import com.VChaikovsky.shapes.reader.impl.ReaderFromFile;
import com.VChaikovsky.shapes.service.impl.ParameterCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws ShapeException {
        ReaderFromFile reader = new ReaderFromFile();
        PyramidParameterParser parser = new PyramidParameterParser();
        PyramidCreator creator = new PyramidCreator();
        ParameterCalculator calculator = new ParameterCalculator();
        List<String> list = reader.readData("sources/pyramids.txt");
        List<double[]> doubleList = parser.parseStrToPyramidParam(list);
        doubleList.forEach(arr->{
            try {
                Pyramid pyramid = creator.createEntity(arr);
                logger.info("Parameters of " + pyramid + " are:\n" +
                        "- high: " + calculator.findPyramidHeight(pyramid) + ";\n" +
                        "- volume: " + calculator.findVolume(pyramid) + ";\n" +
                        "- surface square: " + calculator.findSurfaceSquare(pyramid) + ";\n" +
                        "- pyramid bases lays on the basic plane: " + calculator.isBasesOnBasePlane(pyramid) + ";\n" +
                        "- crossing with basic planes: " + calculator.findVolumeProportion(pyramid));
            } catch (ShapeException e) {
                System.out.println("Data is not correct");
            }
        });
    }
}