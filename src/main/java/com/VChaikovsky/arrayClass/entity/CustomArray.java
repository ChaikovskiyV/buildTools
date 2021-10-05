package com.VChaikovsky.arrayClass.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class CustomArray {
    final static Logger logger = LogManager.getLogger();
    private Integer[] array;

    public CustomArray(Integer[] array) {
        this.array = array;
    }

    public Integer[] getArray() {
        return array.clone();
    }

    public void setArray(Integer[] array) {
        if(array != null) {
            this.array = array.clone();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArray that = (CustomArray) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}