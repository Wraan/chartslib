package com.chartslib.exceptions;

public class InvalidDataException extends RuntimeException{

    private String message;

    public InvalidDataException() {
        message = "No data inserted.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
