package com.VChaikovsky.shapes.repository;

import com.VChaikovsky.shapes.entity.impl.Pyramid;
import com.VChaikovsky.shapes.specification.Specification;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class PyramidRepository {
    private Set<Pyramid> pyramids = new HashSet<>();

    public int size() {
        return pyramids.size();
    }

    public boolean add(Pyramid pyramid) {
        return pyramids.add(pyramid);
    }

    public boolean remove(Pyramid pyramid) {
        return pyramids.remove(pyramid);
    }

    public boolean containsAll(Collection<?> c) {
        return pyramids.containsAll(c);
    }

    public boolean addAll(Collection<? extends Pyramid> c) {
        return pyramids.addAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return pyramids.removeAll(c);
    }

    public void clear() {
        pyramids.clear();
    }

    public Set<Pyramid> query(Specification specification) {
        return pyramids;
    }

    public Set<Pyramid> query(Predicate<Specification> specificationPredicate) {
        return pyramids;
    }
}
