package com.vchaikovsky.shape.observer;

import com.vchaikovsky.shape.event.PyramidEvent;
import com.vchaikovsky.shape.exception.ShapeException;

public interface Observer {
    void parameterChanged(PyramidEvent event) throws ShapeException;
}
