package com.vchaikovsky.shapes.entity.impl;

import com.vchaikovsky.shapes.entity.GeometryEntity;

public record Point(double x, double y, double z) implements GeometryEntity{
}