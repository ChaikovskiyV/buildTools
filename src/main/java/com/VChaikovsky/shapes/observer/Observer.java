package com.VChaikovsky.shapes.observer;

import com.VChaikovsky.shapes.event.PyramidEvent;
import com.VChaikovsky.shapes.exception.ShapeException;

import java.util.EventObject;

public interface Observer {
    void parameterChanged(PyramidEvent event) throws ShapeException;
}
