package com.chartslib.exceptions;

public class InvalidWindowSizeException extends RuntimeException{

    private String message;

    public InvalidWindowSizeException(){
        message = "Window size must be at least 800x600";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
