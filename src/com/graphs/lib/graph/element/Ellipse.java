package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Ellipse implements Drawable{
    private Point middle;
    private int radiusX;
    private int radiusY;
    private PApplet parent;
    private int rgb;

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public Ellipse(PApplet parent, Point middle, int radiusX, int radiusY) {
        this.middle = middle;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.parent = parent;
        this.rgb = 120;
    }
    public Ellipse(PApplet parent, Point middle, int radiusX, int radiusY, int rgb) {
        this.middle = middle;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.parent = parent;
        this.rgb = rgb;
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
        parent.fill(rgb);
        parent.ellipse(middle.getX(), middle.getY(), 2*radiusX, 2*radiusY);
    }
}
