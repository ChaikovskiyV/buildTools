package com.VChaikovsky.shapes.main;

import com.VChaikovsky.shapes.creator.impl.PyramidCreator;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.parser.impl.PyramidParameterParser;
import com.VChaikovsky.shapes.reader.impl.ReaderFromFile;
import com.VChaikovsky.shapes.repository.PyramidRepository;
import com.VChaikovsky.shapes.service.impl.ParameterCalculator;
import com.VChaikovsky.shapes.specification.impl.CornersNumberSpecification;
import com.VChaikovsky.shapes.specification.impl.IdSpecification;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    final static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws ShapeException {
        ReaderFromFile reader = new ReaderFromFile();
        PyramidParameterParser parser = PyramidParameterParser.getInstance();
        PyramidCreator creator = PyramidCreator.getInstance();
        PyramidsWarehouse warehouse = PyramidsWarehouse.getInstance();
        PyramidRepository repository = PyramidRepository.getInstance();

        List<String> list = reader.readData("sources/pyramids.txt");
        List<double[]> doubleList = parser.parseStrToPyramidParam(list);
        doubleList.forEach(arr->{
            try {
                Pyramid pyramid = creator.createEntity(arr);
                logger.info(pyramid + "\n------------------");
            } catch (ShapeException e) {
                System.out.println("Data is not correct");
            }
        });
        warehouse.getParametersMap().entrySet().forEach(id->logger.info(id + "\n++++++++++++++++++++++++++++++"));
        repository.query(new CornersNumberSpecification(3, 7)).forEach(pyramid -> pyramid.setBasesCornersNumber(pyramid.getBasesCornersNumber() + 3));
        warehouse.getParametersMap().entrySet().forEach(id->logger.info(id + "\n********************************"));
    }
}