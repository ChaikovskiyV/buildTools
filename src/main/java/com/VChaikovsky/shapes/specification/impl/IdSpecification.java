package com.VChaikovsky.shapes.specification.impl;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;

public class IdSpecification implements Specification {
    private long minId;
    private long maxId;

    public IdSpecification(long minId, long maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }

    @Override
    public boolean specify(Pyramid pyramid){
        long id = pyramid.getId();

        return id >= minId && id <= maxId;
    }
}