package com.vchaikovsky.shape.main;

import com.vchaikovsky.shape.creator.impl.PyramidCreator;
import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.exception.ShapeException;
import com.vchaikovsky.shape.filler.impl.DataFiller;
import com.vchaikovsky.shape.parser.impl.PyramidParameterParser;
import com.vchaikovsky.shape.reader.impl.ReaderFromFile;
import com.vchaikovsky.shape.repository.PyramidRepository;
import com.vchaikovsky.shape.specification.impl.CornersNumberSpecification;
import com.vchaikovsky.shape.warehouse.PyramidsWarehouse;
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
                logger.info(pyramid);
            } catch (ShapeException e) {
                logger.warn("Data is not correct");
            }
        });
        logger.info("The list of pyramid's parameters from warehouse:\n ");
        PyramidsWarehouse.getInstance()
                .getParametersMap()
                .entrySet()
                .forEach(p->logger.info("id = " + p ));
        PyramidRepository.getInstance()
                .query(new CornersNumberSpecification(3, 7))
                .forEach(pyramid -> pyramid.setBasesCornersNumber(pyramid.getBasesCornersNumber() + 3));
        logger.info("The list of pyramid's parameters from warehouse after changing them:\n ");
        PyramidsWarehouse.getInstance()
                .getParametersMap()
                .entrySet()
                .forEach(p->logger.info("id = " + p));
    }
}