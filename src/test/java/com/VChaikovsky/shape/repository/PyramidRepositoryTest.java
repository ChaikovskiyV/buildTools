package com.vchaikovsky.shape.repository;

import com.vchaikovsky.shape.entity.impl.Point;
import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.exception.ShapeException;
import com.vchaikovsky.shape.filler.impl.DataFiller;
import com.vchaikovsky.shape.service.impl.ParameterCalculator;
import com.vchaikovsky.shape.specification.Specification;
import com.vchaikovsky.shape.specification.impl.*;
import com.vchaikovsky.shape.warehouse.PyramidsWarehouse;
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
    private Point pointThree;
    private Point pointFour;
    private Pyramid pyramidOne;
    private Pyramid pyramidTwo;
    private Pyramid pyramidThree;
    private Pyramid pyramidFour;
    private double radiusOne;
    private double radiusTwo;
    private double radiusThree;
    private double radiusFour;
    private int cornersNumberOne;
    private int cornersNumberTwo;
    private int cornersNumberThree;
    private int cornersNumberFour;
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
        pointThree = new Point(1, 3, 3);
        pointFour = new Point(1, 10, 3);
        radiusOne = 10.5;
        radiusTwo = 20;
        radiusThree = 15;
        radiusFour = 25;
        cornersNumberOne = 4;
        cornersNumberTwo = 6;
        cornersNumberThree = 8;
        cornersNumberFour = 3;
        pyramidOne = new Pyramid(pointOne, pointTwo, cornersNumberOne, radiusOne);
        dataFiller.addWareHouseAndRepository(pyramidOne);
        pyramidTwo = new Pyramid(pointTwo, pointFour, cornersNumberTwo, radiusTwo);
        dataFiller.addWareHouseAndRepository(pyramidTwo);
        pyramidThree = new Pyramid(pointThree, pointTwo, cornersNumberThree, radiusThree);
        dataFiller.addWareHouseAndRepository(pyramidThree);
        pyramidFour = new Pyramid(pointFour, pointOne, cornersNumberFour, radiusFour);
        dataFiller.addWareHouseAndRepository(pyramidFour);
        expectedSet = new HashSet<>();
        expectedList = new ArrayList<>();
    }

    @AfterEach
    void cleanExpected() {
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
    public void queryStreamId() {
        specification = new IdSpecification(2, 5);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidTwo, pyramidThree, pyramidFour);
        assertEquals(expectedSet, result);
    }

    @Test
    public void queryStreamExactId() {
        specification = new IdSpecification(3);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidThree);
        assertEquals(expectedSet, result);
    }

    @Test
    public void queryId() {
        specification = new IdSpecification(1, 3);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidTwo, pyramidThree);
        assertEquals(expectedSet, result);
    }

    @Test
    public void queryExactId() {
        specification = new IdSpecification(1);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidOne);
        assertEquals(expectedSet, result);
    }

    @Test
    public void queryStreamCornersNumber() {
        specification = new CornersNumberSpecification(3, 5);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidFour);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryCornersNumber() {
        specification = new CornersNumberSpecification(5, 10);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidTwo, pyramidThree);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryStreamRadius() {
        specification = new BasesRadiusSpecification(15, 20);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidTwo, pyramidThree);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryRadius() {
        specification = new BasesRadiusSpecification(10, 15);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidThree);

        assertEquals(expectedSet, result);
    }

    @Test
    public void  queryStreamVolume() {
        specification = new VolumeSpecification(2200, 6500);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidTwo, pyramidThree);

        assertEquals(expectedSet, result);
    }

    @Test
    public void  queryVolume() {
        specification = new VolumeSpecification(300, 2200);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidFour);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryStreamSurfaceSquare() {
        specification = new SurfaceSquareSpecification(400, 1000);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidOne);

        assertEquals(expectedSet, result);
    }

    @Test
    public void querySurfaceSquare() {
        specification = new SurfaceSquareSpecification(1100, 2600);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidTwo, pyramidThree, pyramidFour);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryStreamHeight() {
        specification = new HeightSpecification(8,15);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidThree, pyramidFour);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryHeight() {
        specification = new HeightSpecification(15,20);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidTwo);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryStreamDistanceFromCenterPointToZeroPoint() {
        specification = new BasesCenterPointSpecification(0, 10);
        result = repository.queryStream(specification);
        Collections.addAll(expectedSet, pyramidOne, pyramidTwo, pyramidThree);

        assertEquals(expectedSet, result);
    }

    @Test
    public void queryDistanceFromCenterPointToZeroPoint() {
        specification = new BasesCenterPointSpecification(8, 20);
        result = repository.query(specification);
        Collections.addAll(expectedSet, pyramidTwo, pyramidFour);

        assertEquals(expectedSet, result);
    }

    @Test
    public void sortId() {
        List<Pyramid> result = repository.sort((p1, p2)-> (int) (p1.getId() - p2.getId()));
        Collections.addAll(expectedList, pyramidOne, pyramidTwo, pyramidThree, pyramidFour);

        assertEquals(expectedList, result);
    }

    @Test
    public void sortVolume() {
        List<Pyramid> result = repository.sort((p1, p2)-> {
                double volume1 = warehouse
                        .get(p1.getId())
                        .pyramidVolume();
                double volume2 = warehouse
                        .get(p2.getId())
                        .pyramidVolume();
                return Double.compare(volume1, volume2);
        });

        Collections.addAll(expectedList, pyramidOne, pyramidFour, pyramidThree, pyramidTwo);

        assertEquals(expectedList, result);
    }

    @Test
    public void sortSurfaceSquare() {
        List<Pyramid> result = repository.sort((p1, p2)-> {
                double surfaceSquare1 = warehouse
                        .get(p1.getId())
                        .surfaceSquare();
                double surfaceSquare2 = warehouse
                        .get(p2.getId())
                        .surfaceSquare();
                return Double.compare(surfaceSquare1, surfaceSquare2);
        });

        Collections.addAll(expectedList, pyramidOne, pyramidThree, pyramidFour, pyramidTwo);

        assertEquals(expectedList, result);
    }

    @Test
    public void sortHeight() {
        List<Pyramid> result = repository.sort((p1, p2)-> {
            double height1 = PyramidsWarehouse.getInstance()
                    .get(p1.getId())
                    .height();
            double height2 = PyramidsWarehouse.getInstance()
                    .get(p2.getId())
                    .height();
            return Double.compare(height1, height2);
        });
        Collections.addAll(expectedList, pyramidFour, pyramidOne, pyramidThree, pyramidTwo);

        assertEquals(expectedList, result);
    }

    @Test
    public void sortRadius() {
        List<Pyramid> result = repository.sort((p1, p2)-> Double.compare(p1.getCircumcircleRadius(), p2.getCircumcircleRadius()));
        Collections.addAll(expectedList, pyramidOne, pyramidThree, pyramidTwo, pyramidFour);

        assertEquals(expectedList, result);
    }

    @Test
    public void sortCornersNumber() {
        List<Pyramid> result = repository.sort((p1, p2)-> p1.getBasesCornersNumber() - p2.getBasesCornersNumber());
        Collections.addAll(expectedList, pyramidFour, pyramidOne, pyramidTwo, pyramidThree);

        assertEquals(expectedList, result);
    }

    @Test
    public void sortDistanceFromCenterPointToZeroPoint() {
        List<Pyramid> result = repository.sort((p1, p2)-> {
            double distance1 = ParameterCalculator.getInstance()
                    .findDistanceToZeroPoint(p1.getBasesCenter());
            double distance2 = ParameterCalculator.getInstance()
                    .findDistanceToZeroPoint(p2.getBasesCenter());
            return Double.compare(distance1, distance2);
        });
        Collections.addAll(expectedList, pyramidOne, pyramidThree, pyramidTwo, pyramidFour);

        assertEquals(expectedList, result);
    }
}