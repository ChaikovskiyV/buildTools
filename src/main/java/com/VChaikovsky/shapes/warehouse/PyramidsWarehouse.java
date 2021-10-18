package com.VChaikovsky.shapes.warehouse;

import com.VChaikovsky.shapes.entity.impl.PyramidParameters;
import com.VChaikovsky.shapes.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class PyramidsWarehouse {
    Logger logger = LogManager.getLogger();
    private Map<Long, PyramidParameters> parametersMap = new HashMap<>();
    private static PyramidsWarehouse instance;

    private PyramidsWarehouse() {}

    public static PyramidsWarehouse getInstance() {
        if(instance == null) {
            instance = new PyramidsWarehouse();
        }
        return instance;
    }

    public Map<Long, PyramidParameters> getParametersMap() {
        return Map.copyOf(parametersMap);
    }

    public PyramidParameters get(Long id) throws ShapeException {
        if(!parametersMap.containsKey(id)) {
            logger.error("Data with " + id + "was not found.");
            throw new ShapeException("Data with " + id + "was not found.");
        }
        PyramidParameters pyramidParameters;
        pyramidParameters = parametersMap.get(id);
        /*try {
            pyramidParameters = parametersMap.get(id).clone();
        } catch (CloneNotSupportedException e) {                                //If return clone tests fail.
            logger.error("Exception from clone PyramidParameters.");
            throw new ShapeException("Exception from clone PyramidParameters.", e);
        }*/
        return pyramidParameters;
    }

    public PyramidParameters put(Long id, PyramidParameters value) {
        return parametersMap.put(id, value);
    }

    public PyramidParameters remove(Long id) throws ShapeException {
        if(!parametersMap.containsKey(id)) {
            logger.error("Data with " + id + "was not found.");
            throw new ShapeException("Data with " + id + "was not found.");
        }
        return parametersMap.remove(id);
    }
}