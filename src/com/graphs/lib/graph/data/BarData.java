package com.graphs.lib.graph.data;

public class BarData {
    private double data;
    private String label;

    public BarData() {
    }

    public BarData(String label, double data) {
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