package com.chartslib.data;

import com.chartslib.element.Color;
import com.chartslib.element.Point;

import java.util.List;

public class PointData {
    private List<Point> data;
    private String label;
    private Color color;

    public PointData(String label, List<Point> data) {
        this.data = data;
        this.label = label;
    }
    public PointData(String label, List<Point> data, Color color) {
        this.data = data;
        this.label = label;
        this.color = color;
    }

    public List<Point> getData() {
        return data;
    }

    public void setData(List<Point> data) {
        this.data = data;
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
