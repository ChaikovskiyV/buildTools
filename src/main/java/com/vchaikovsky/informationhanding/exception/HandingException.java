package com.vchaikovsky.informationhanding.exception;

public class HandingException extends Exception{
    public HandingException() {
    }

    public HandingException(String message) {
        super(message);
    }

    public HandingException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandingException(Throwable cause) {
        super(cause);
    }
}