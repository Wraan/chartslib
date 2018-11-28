package com.graphs.lib.graph.element;

public class LegendItem {
    private String label;
    private Color color;

    public LegendItem(){}

    public LegendItem(String label, Color color) {
        this.label = label;
        this.color = color;
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
