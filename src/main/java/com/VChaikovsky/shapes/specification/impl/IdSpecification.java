package com.vchaikovsky.shapes.specification.impl;

import com.vchaikovsky.shapes.entity.impl.Pyramid;
import com.vchaikovsky.shapes.specification.Specification;

public class IdSpecification implements Specification {
    private long minId;
    private long maxId;
    private long id;

    public IdSpecification(long minId, long maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }

    public IdSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean specify(Pyramid pyramid) {
        long id = pyramid.getId();

        return this.id == 0? id >= minId && id <= maxId : this.id == id;
    }
}