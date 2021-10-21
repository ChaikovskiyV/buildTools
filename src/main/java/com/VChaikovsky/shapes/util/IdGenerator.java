package com.VChaikovsky.shapes.util;

public class IdGenerator {
    private static long idCount;

    private IdGenerator(){}

    public static long generateId(){
        return ++idCount;
    }
}