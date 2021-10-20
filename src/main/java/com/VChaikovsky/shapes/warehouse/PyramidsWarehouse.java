package com.VChaikovsky.shapes.warehouse;

import com.VChaikovsky.shapes.entity.impl.PyramidParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class PyramidsWarehouse {
    static final Logger logger = LogManager.getLogger();
    private static PyramidsWarehouse instance;
    private Map<Long, PyramidParameters> parametersMap = new HashMap<>();

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

    public PyramidParameters get(long id) {
        return parametersMap.get(id);
    }

    public PyramidParameters put(long id, PyramidParameters value) {
        return parametersMap.put(id, value);
    }

    public PyramidParameters remove(long id) {
        return parametersMap.remove(id);
    }
}