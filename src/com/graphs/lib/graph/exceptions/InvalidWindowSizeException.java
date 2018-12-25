package com.graphs.lib.graph.exceptions;

public class InvalidWindowSizeException extends Exception{

    private String message;

    public InvalidWindowSizeException(){
        message = "Window size must be at least 800x600";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
