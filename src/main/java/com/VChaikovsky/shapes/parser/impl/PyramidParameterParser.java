package com.VChaikovsky.shapes.parser.impl;

import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.parser.PyramidParameterParserInt;
import com.VChaikovsky.shapes.validator.impl.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Stream;

public class PyramidParameterParser implements PyramidParameterParserInt {
    final static Logger logger = LogManager.getLogger();
    private final String DELIMITER = "\\s";

    @Override
    public List<double[]> parseStrToPyramidParam(List<String> list) throws ShapeException {
        DataValidator validator = new DataValidator();
        List<double[]> arraysList;

        if(list.isEmpty()) {
            logger.error("Source data is empty or wrong.");
            throw new ShapeException("Source data is empty or wrong.");
        }
            arraysList = list
                    .stream()
                    .map(x->x.split(DELIMITER))
                    .filter(s-> validator.isValidParam(s))
                    .map(arr->parseStrArrToDoubleArr(arr))
                    .toList();

        return arraysList;
    }

    private double[] parseStrArrToDoubleArr(String[] strArray){
        Double[] doubles = Stream
                .of(strArray)
                .map(s->Double.parseDouble(s))
                .toArray(Double[]::new);

        return parseDoubleArrToPrimitive(doubles);
    }

    private double[] parseDoubleArrToPrimitive(Double [] array){
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}