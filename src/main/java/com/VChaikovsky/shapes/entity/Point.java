package com.VChaikovsky.shapes.entity;

import com.VChaikovsky.shapes.idgenerator.IdGenerator;

public record Point(double x, double y, double z) {
    private static final long ID;

    static {
        ID = IdGenerator.generateId();
    }

    public static long getID() {
        return ID;
    }
}