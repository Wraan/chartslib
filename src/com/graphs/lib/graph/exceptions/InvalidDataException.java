package com.graphs.lib.graph.exceptions;

public class InvalidDataException extends Exception{

    private String message;

    public InvalidDataException() {
        message = "No data inserted.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
