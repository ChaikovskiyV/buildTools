package com.VChaikovsky.shapes.event;

import com.VChaikovsky.shapes.entity.impl.Pyramid;

import java.util.EventObject;

public class PyramidEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public PyramidEvent(Pyramid source) {
        super(source);
    }

    public Pyramid getPyramid() {
        return (Pyramid) super.getSource();
    }
}