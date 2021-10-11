package com.VChaikovsky.shapes.idgenerator;

public class IdGenerator {
    private static long idCount = 0;

    private IdGenerator(){}

    public static long generateId(){
        return ++idCount;
    }
}
