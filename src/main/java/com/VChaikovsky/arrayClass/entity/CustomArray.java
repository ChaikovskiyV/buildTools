package com.VChaikovsky.arrayClass.entity;

import java.util.Arrays;

public class CustomArray {
    private Double[] array;

    public CustomArray(Double[] array) {
        this.array = array;
    }

    public Double[] getArray() {
        return array.clone();
    }

    public void setArray(Double[] array) {
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