package com.vchaikovsky.shape.repository;

import com.vchaikovsky.shape.entity.impl.Pyramid;
import com.vchaikovsky.shape.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class PyramidRepository {
    static final Logger logger = LogManager.getLogger();
    private static PyramidRepository instance;
    private Set<Pyramid> pyramids = new HashSet<>();

    private PyramidRepository() {}

    public static PyramidRepository getInstance() {
        if(instance == null) {
            instance = new PyramidRepository();
        }
        return instance;
    }

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

    public Set<Pyramid> queryStream(Specification specification) {
        return pyramids.stream()
                .filter(specification :: specify)
                .collect(Collectors.toSet());
    }

    public Set<Pyramid> query(Specification specification) {
        Set<Pyramid> queryResult = new HashSet<>();
        pyramids.forEach(p->{
                if(specification.specify(p)){
                    queryResult.add(p);
                }
        });
        return queryResult;
    }

    public List<Pyramid> sort(Comparator<? super Pyramid> comparator){
        return pyramids.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}