package com.graphs.lib.graph.element;

import processing.core.PApplet;
import processing.core.PConstants;

public class Polygon implements Drawable {
    private Color color = new Color(0,0,0);
    private Color outColor = new Color(0,0,0);
    private int thickness = 1;
    private boolean isFill = true;
    private Point[] data;
    private PApplet parent;

    public Polygon(PApplet parent, Point[] data) {
        this.setData(data);
        this.parent = parent;
    }

    @Override
    public void draw() {
        if(getIsFill())
            parent.fill(getColor().getR(), getColor().getG(), getColor().getB());
        else
            parent.noFill();
        parent.stroke(getOutColor().getR(), getOutColor().getG(), getOutColor().getB());
        parent.strokeWeight(getThickness());
        parent.beginShape();
        for(Point point : getData()) {
            parent.vertex(point.getX(), point.getY());
        }
        parent.endShape(PConstants.CLOSE);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getOutColor() {
        return outColor;
    }

    public void setOutColor(Color outColor) {
        this.outColor = outColor;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public boolean getIsFill() {
        return isFill;
    }

    public void setIsFill(boolean isFill) {
        this.isFill = isFill;
    }

    public Point[] getData() {
        return data;
    }

    public void setData(Point[] data) {
        this.data = data;
    }
}
