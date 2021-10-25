package com.vchaikovsky.shapes.observer;

import com.vchaikovsky.shapes.event.PyramidEvent;
import com.vchaikovsky.shapes.exception.ShapeException;

public interface Observer {
    void parameterChanged(PyramidEvent event) throws ShapeException;
}
