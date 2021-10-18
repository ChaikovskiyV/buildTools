package com.VChaikovsky.shapes.comporator;

import com.VChaikovsky.shapes.entity.impl.Point;
import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.exception.ShapeException;
import com.VChaikovsky.shapes.service.impl.ParameterCalculator;
import com.VChaikovsky.shapes.warehouse.PyramidsWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public enum PyramidComparator {
    ID,
    BASES_CENTER_POINT,
    HEIGHT,
    VOLUME,
    SURFACE_SQUARE,
    RADIUS,
    CORNERS_NUMBER;

    final static Logger logger = LogManager.getLogger();

    Comparator<Pyramid> getComparator(){
        return switch (this) {
            case ID -> Comparator.comparingLong(Pyramid::getId);
            case RADIUS -> Comparator.comparingDouble(Pyramid::getCircumcircleRadius);
            case CORNERS_NUMBER -> Comparator.comparingInt(Pyramid::getBasesCornersNumber);
            case HEIGHT -> Comparator.comparingDouble((ToDoubleFunction<? super Pyramid>) p->
            {
                double height = 0;
                try {
                    height =  PyramidsWarehouse
                            .getInstance()
                            .get(p.getId())
                            .height();
                } catch (ShapeException e) {
                    logger.warn("Exception from height comparator.", e);
                }
                return height;
            });
            case VOLUME -> Comparator.comparingDouble((ToDoubleFunction<? super Pyramid>) p-> {
                double volume = 0;
                try {
                    volume = PyramidsWarehouse
                            .getInstance()
                            .get(p.getId())
                            .pyramidVolume();
                } catch (ShapeException e) {
                    logger.warn("Exception from volume comparator.", e);
                }
                return volume;
            });
            case SURFACE_SQUARE -> Comparator.comparingDouble((ToDoubleFunction<? super Pyramid>) p-> {
                double surfaceSquare = 0;
                try {
                    surfaceSquare = PyramidsWarehouse
                            .getInstance()
                            .get(p.getId())
                            .surfaceSquare();
                } catch (ShapeException e) {
                    logger.warn("Exception from surface square comparator.", e);
                }
                return surfaceSquare;
            });
            case BASES_CENTER_POINT -> Comparator.comparingDouble((ToDoubleFunction<? super Pyramid>) p-> {
                        Point basesCenter = p.getBasesCenter();
                        double distanceToZeroPoint = ParameterCalculator
                                .getInstance()
                                .findDistanceToZeroPoint(basesCenter);
                return distanceToZeroPoint;
                    });
            default -> Comparator.comparingLong(Pyramid::getId);
        };
    }
}