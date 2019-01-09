package com.graphs.lib.graph.data;

public class ColumnData {
    private double data;
    private String label;

    public ColumnData() {
    }

    public ColumnData(String label,double data) {
        this.data = data;
        this.label = label;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}