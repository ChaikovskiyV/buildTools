package com.VChaikovsky.shapes.repository;

import com.VChaikovsky.shapes.entity.impl.Point;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.filler.impl.DataFiller;
import com.VChaikovsky.shapes.specification.Specification;
import com.VChaikovsky.shapes.specification.impl.BasesRadiusSpecification;
import com.VChaikovsky.shapes.specification.impl.CornersNumberSpecification;
import com.VChaikovsky.shapes.specification.impl.IdSpecification;
import com.VChaikovsky.shapes.specification.impl.VolumeSpecification;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PyramidRepositoryTest {
    static final Logger logger = LogManager.getLogger();
    private PyramidRepository repository;
    private PyramidsWarehouse warehouse;
    private DataFiller dataFiller;
    private Specification specification;
    private Point pointOne;
    private Point pointTwo;
    private Pyramid pyramidOne;
    private Pyramid pyramidTwo;
    private Pyramid pyramidThree;
    private Pyramid pyramidFour;
    double radiusOne;
    private double radiusTwo;
    private int cornersNumberOne;
    private int cornersNumberTwo;
    private Set<Pyramid> pyramidSet;
    private Set<Pyramid> expectedSet;
    private Set<Pyramid> result;
    private List<Pyramid> expectedList;

    @BeforeAll
    void setUp() throws ShapeException {
        logger.info("The testing is starting ...");
        repository = PyramidRepository.getInstance();
        warehouse = PyramidsWarehouse.getInstance();
        dataFiller = DataFiller.getInstance();
        pointOne = new Point(1, 2, 3);
        pointTwo = new Point(1, -8, 3);
        radiusOne = 10.5;
        radiusTwo = 20;
        cornersNumberOne = 3;
        cornersNumberTwo = 6;
        pyramidOne = new Pyramid(pointOne, pointTwo, cornersNumberOne, radiusOne);
        dataFiller.addWareHouseAndRepository(pyramidOne);
        pyramidTwo = new Pyramid(pointTwo, pointOne, cornersNumberTwo, radiusOne);
        dataFiller.addWareHouseAndRepository(pyramidTwo);
        pyramidThree = new Pyramid(pointOne, pointTwo, cornersNumberOne, radiusTwo);
        dataFiller.addWareHouseAndRepository(pyramidThree);
        pyramidFour = new Pyramid(pointTwo, pointOne, cornersNumberTwo, radiusTwo);
        dataFiller.addWareHouseAndRepository(pyramidFour);
        pyramidSet = new HashSet<>();
        expectedSet = new HashSet<>();
        expectedList = new ArrayList<>();
    }

    @BeforeEach
    void setRepository() {
        Collections.addAll(pyramidSet, pyramidOne, pyramidTwo, pyramidThree, pyramidFour);
        //repository.addAll(pyramidSet);
    }

    @AfterEach
    void cleanRepository() {
        //repository.clear();
        if(!expectedSet.isEmpty()){
            expectedSet.clear();
        }
        if(!expectedList.isEmpty()){
            expectedList.clear();
        }
    }

    @AfterAll
    void tearDown() {
        logger.info("The tests have been finished.");
    }

    @Test
    void queryStreamId() {
        specification = new IdSpecification(1, 2);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidTwo);

        assertEquals(expectedSet, result);
    }

    @Test
    void queryStreamCornersNumber() {
        specification = new CornersNumberSpecification(3, 5);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidThree);

        assertEquals(expectedSet, result);
    }

    @Test
    void queryRadius() {
        specification = new BasesRadiusSpecification(15, 20);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidThree, pyramidFour);

        assertEquals(expectedSet, result);
    }

    @Test
    void  queryVolume() {
        specification = new VolumeSpecification(300, 2000);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidTwo, pyramidThree);

        assertEquals(expectedSet, result);
    }

    @Test
    void sortId() {
        List<Pyramid> result = repository.sort((p1, p2)-> (int) (p1.getId() - p2.getId()));
        Collections.addAll(expectedList, pyramidOne, pyramidTwo, pyramidThree, pyramidFour);

        assertEquals(expectedList, result);
    }

    @Test
    void sortVolume() {
        List<Pyramid> result = repository.sort((p1, p2)-> {
                double volume1 = warehouse
                        .get(p1.getId())
                        .pyramidVolume();
                double volume2 = warehouse
                        .get(p2.getId())
                        .pyramidVolume();
                return Double.compare(volume1, volume2);
        });

        Collections.addAll(expectedList, pyramidOne, pyramidTwo, pyramidThree, pyramidFour);

        assertEquals(expectedList, result);
    }

    @Test
    void sortSurfaceSquare() {
        List<Pyramid> result = repository.sort((p1, p2)-> {
                double surfaceSquare1 = warehouse
                        .get(p1.getId())
                        .surfaceSquare();
                double surfaceSquare2 = warehouse
                        .get(p2.getId())
                        .surfaceSquare();
                return Double.compare(surfaceSquare1, surfaceSquare2);
        });

        Collections.addAll(expectedList, pyramidOne, pyramidTwo, pyramidThree, pyramidFour);

        assertEquals(expectedList, result);
    }
}