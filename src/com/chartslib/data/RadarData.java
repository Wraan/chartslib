package com.chartslib.data;

import com.chartslib.element.Color;

public class RadarData {
    //Fields
    private String                  label;
    private double[]                data;
    private Color                   color;
    //~Fields
    //--------------------------------------------------------------------------------------------
    //Constructors
    public RadarData(double[] data, String label, Color color) {
        this.data = data;
        this.label = label;
        this.color = color;
    }

    public RadarData(double[] data, String label) {
        this(data, label, null);
    }
    //~Constructors
    //--------------------------------------------------------------------------------------------
    //Getter and Setter
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
    //~Getter and Setter
}
