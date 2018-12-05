package com.graphs.lib.graph.data;

import com.graphs.lib.graph.element.Color;

public class RadarData {
    private String label;
    private double[] data;
    private Color color;

    public RadarData(double[] data, String label, Color color) {
        this(data, label);
        this.color = color;
    }

    public RadarData(double[] data, String label) {
        this.data = data;
        this.label = label;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public Color getColor() {return color; }

    public void setColor(Color color) { this.color = color; }
}
