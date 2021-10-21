package com.VChaikovsky.shapes.warehouse;

import com.VChaikovsky.shapes.entity.impl.PyramidParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PyramidsWarehouseTest {
    static final Logger logger = LogManager.getLogger();
    private PyramidsWarehouse warehouse;
    private Map<Long, PyramidParameters> expectedMap;
    private PyramidParameters expectedPyramidParameters;
    long id;

    @BeforeAll
    void setUp() {
        logger.info("Testing is starting ...");
        warehouse = PyramidsWarehouse.getInstance();
        expectedMap = new HashMap<>();
        expectedPyramidParameters = new PyramidParameters(20, 10, 1000,2000, true, "25 : 75");
        id = 1;
        expectedMap.put(id, expectedPyramidParameters);
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    public void put() {
        warehouse.put(id, expectedPyramidParameters);
        boolean result = warehouse.getParametersMap()
                .containsValue(expectedPyramidParameters);

        assertTrue(result);
    }

    @Test
    public void getParametersMap() {
        warehouse.put(id, expectedPyramidParameters);
        Map<Long, PyramidParameters> result = warehouse.getParametersMap();

        assertEquals(expectedMap, result);
    }

    @Test
    public void get() {
        warehouse.put(id, expectedPyramidParameters);
        PyramidParameters result = warehouse.get(id);

        assertEquals(expectedPyramidParameters, result);
    }

    @Test
    public void remove() {
        warehouse.remove(id);
        boolean result = warehouse.getParametersMap().containsKey(id);

        assertFalse(result);
    }
}