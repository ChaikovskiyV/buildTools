package com.vchaikovsky.shape.entity.impl;

import com.vchaikovsky.shape.entity.GeometryEntity;

public record Point(double x, double y, double z) implements GeometryEntity{
}