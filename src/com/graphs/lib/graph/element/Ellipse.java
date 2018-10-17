package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Ellipse implements Drawable{
    private Point middle;
    private int radiusX;
    private int radiusY;
    private PApplet parent;
    private Color color = new Color(0,0,0);
    private Color outColor = new Color(0,0,0);
    private int thickness = 1;

    public Ellipse(PApplet parent, Point middle, int radiusX, int radiusY) {
        this.middle = middle;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.parent = parent;
    }

    public Ellipse(PApplet parent, Point middle, int radiusX, int radiusY, Color color) {
        this.middle = middle;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.parent = parent;
        this.color = color;
    }

    public Ellipse(PApplet parent, Point middle, int radiusX, int radiusY, Color color, int thickness, Color outColor) {
        this.middle = middle;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.parent = parent;
        this.color = color;
        this.thickness = thickness;
        this.outColor = outColor;
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

    public int getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(int radiusX) {
        this.radiusX = radiusX;
    }

    public int getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(int radiusY) {
        this.radiusY = radiusY;
    }

    public Point getMiddle() {
        return middle;
    }

    public void setMiddle(Point middle) {
        this.middle = middle;
    }

    public void draw(){
        parent.fill(color.getR(), color.getG(), color.getB());
        parent.stroke(outColor.getR(), outColor.getG(), outColor.getB());
        parent.strokeWeight(thickness);
        parent.ellipse(middle.getX(), middle.getY(), 2*radiusX, 2*radiusY);
    }
}
