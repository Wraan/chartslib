package com.graphs.lib.graph.data;

import com.graphs.lib.graph.element.Color;

public class PieData {
    private double data;
    private String label;
    private Color color;

    public PieData() {
    }
    public PieData(String label, double value) {
        this.data = value;
        this.label = label;
    }
    public PieData(String label, double value, Color color){
        this.data = value;
        this.label = label;
        this.color = color;
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
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
