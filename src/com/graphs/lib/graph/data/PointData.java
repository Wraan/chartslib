package com.graphs.lib.graph.data;

import com.graphs.lib.graph.element.Point;

import java.util.List;

public class PointData {
    private List<Point> data;
    private String label;

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

    public PointData(String label, List<Point> data) {
        this.data = data;
        this.label = label;
    }
}
