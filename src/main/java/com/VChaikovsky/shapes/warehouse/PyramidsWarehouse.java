package com.VChaikovsky.shapes.warehouse;

import java.util.HashMap;
import java.util.Map;

public class PyramidsWarehouse {
    private Map<Long, PyramidParameters> parametersMap = new HashMap<>();
    private static PyramidsWarehouse instance;

    private PyramidsWarehouse() {}

    public static PyramidsWarehouse getInstance() {
        if(instance == null) {
            instance = new PyramidsWarehouse();
        }
        return instance;
    }

    public int size() {
        return parametersMap.size();
    }

    public boolean isEmpty() {
        return parametersMap.isEmpty();
    }

    public PyramidParameters get(Long id) {
        return parametersMap.get(id);
    }

    public PyramidParameters put(Long id, PyramidParameters value) {
        return parametersMap.put(id, value);
    }

    public PyramidParameters remove(Long id) {
        return parametersMap.remove(id);
    }

    public void putAll(Map<? extends Long, ? extends PyramidParameters> m) {
        parametersMap.putAll(m);
    }

    public boolean replace(Long id, PyramidParameters oldValue, PyramidParameters newValue) {
        return parametersMap.replace(id, oldValue, newValue);
    }

    public PyramidParameters replace(Long id, PyramidParameters value) {
        return parametersMap.replace(id, value);
    }
}
