package com.chartslib.exceptions;

public class InvalidValueException extends RuntimeException {

    private String message;

    public InvalidValueException() {
        message = "Invalid data inserted";
    }

    public InvalidValueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
