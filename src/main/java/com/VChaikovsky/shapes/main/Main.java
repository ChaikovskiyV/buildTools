package com.VChaikovsky.shapes.main;

import com.VChaikovsky.shapes.creator.impl.PyramidCreator;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.filler.impl.DataFiller;
import com.VChaikovsky.shapes.parser.impl.PyramidParameterParser;
import com.VChaikovsky.shapes.reader.impl.ReaderFromFile;
import com.VChaikovsky.shapes.repository.PyramidRepository;
import com.VChaikovsky.shapes.specification.impl.CornersNumberSpecification;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws ShapeException {
        List<String> list = ReaderFromFile
                .getInstance()
                .readData("sources/pyramids.txt");
        logger.info("The list of pyramids from repository:\n ");
        List<double[]> doubleList = PyramidParameterParser
                .getInstance()
                .parseStrToPyramidParam(list);
        doubleList.forEach(arr->{
            try {
                Pyramid pyramid = PyramidCreator
                        .getInstance()
                        .createEntity(arr);
                DataFiller.getInstance()
                                .addWareHouseAndRepository(pyramid);
                logger.info(pyramid + "\n-------------------------------------");
            } catch (ShapeException e) {
                System.out.println("Data is not correct");
            }
        });
        logger.info("The list of pyramid's parameters from warehouse:\n ");
        PyramidsWarehouse.getInstance()
                .getParametersMap()
                .entrySet()
                .forEach(p->logger.info("id = " + p + "\n++++++++++++++++++++++++++++++"));
        PyramidRepository.getInstance()
                .query(new CornersNumberSpecification(3, 7))
                .forEach(pyramid -> pyramid.setBasesCornersNumber(pyramid.getBasesCornersNumber() + 3));
        logger.info("The list of pyramid's parameters from warehouse after changing them:\n ");
        PyramidsWarehouse.getInstance()
                .getParametersMap()
                .entrySet()
                .forEach(p->logger.info("id = " + p + "\n********************************"));
    }
}