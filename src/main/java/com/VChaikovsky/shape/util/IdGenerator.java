package com.vchaikovsky.shape.util;

public class IdGenerator {
    private static long idCount;

    private IdGenerator(){}

    public static long generateId(){
        return ++idCount;
    }
}