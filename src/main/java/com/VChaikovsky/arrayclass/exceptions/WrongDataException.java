package com.VChaikovsky.arrayclass.exceptions;

public class WrongDataException extends Exception{

    public WrongDataException(){
        super();
    }

    public WrongDataException(String message){
        super(message);
    }

    public WrongDataException(String message, Exception e){
        super(message, e);
    }

    public WrongDataException(Exception e){
        super(e);
    }
}
