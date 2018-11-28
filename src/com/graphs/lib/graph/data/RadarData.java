package com.graphs.lib.graph.data;

import com.graphs.lib.graph.element.Color;

public class RadarData {
    private String label;
    private double[] data;
    private Color seriesColor;

    public RadarData(double[] data, String label, Color seriesColor) {
        this(data, label);
        this.seriesColor = seriesColor;
    }

    public RadarData(double[] data, String label) {
        this(data);
        this.label = label;
    }

    public RadarData(double[] data) {
        this.data = data;
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

    public Color getSeriesColor() {return seriesColor; }

    public void setSeriesColor(Color seriesColor) { this.seriesColor = seriesColor; }
}
