package com.graphs.lib.graph.data;

public class PieData {
    private double data;
    private String label;

    public PieData() {
    }

    public PieData(String label, double value) {
        this.data = value;
        this.label = label;
    }


    public double getData() {
        return data;
    }

    public void setData(double value) {
        this.data = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
