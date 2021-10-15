package com.VChaikovsky.shapes.entity.impl;

import com.VChaikovsky.shapes.entity.GeometryEntity;

import java.util.Objects;

public class Point implements GeometryEntity {
    //private long id;//if id will be necessary
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

   /* public void setId(long id) { //if id will be necessary
        if(id == 0) {
            this.id = id;
        }
    }

    public long getId() {
        return id;
    }*/

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0 && Double.compare(point.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result = (int) Math.round(x * y + z);
        result = result * (x == 0 ? 1 : (int) x);
        result = result * (y == 0 ? 1 : (int) y);
        result = result * (z == 0 ? 1 : (int) z);

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("Point{")
                .append("x=")
                .append(x)
                .append(", y=")
                .append(y)
                .append(", z=")
                .append(z)
                .append('}')
                .toString();
    }
}