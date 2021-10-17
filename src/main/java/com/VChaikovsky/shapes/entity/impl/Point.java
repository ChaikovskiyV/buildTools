package com.VChaikovsky.shapes.entity.impl;

import com.VChaikovsky.shapes.entity.GeometryEntity;

public record Point(double x, double y, double z) implements GeometryEntity{
}