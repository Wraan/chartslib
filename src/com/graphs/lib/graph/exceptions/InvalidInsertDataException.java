package com.graphs.lib.graph.exceptions;

public class InvalidInsertDataException extends Exception{

    String message;

    public InvalidInsertDataException(){
        message ="Label names must match and be placed in the same order.";
    }

    public InvalidInsertDataException(int maxSeries){
        message = "Number of series exceeded. Max number of series: " + Integer.toString(maxSeries)+".";
    }

    public InvalidInsertDataException(int numberOfLabels, int insertedNumberOfLabels){
        message = "Invalid number of labels. Labels in first ring: " + Integer.toString(numberOfLabels) +", inserted labels: "+Integer.toString(insertedNumberOfLabels)+".";
    }


    @Override
    public String getMessage() {
        return message;
    }
}
