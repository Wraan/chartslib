package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Circle implements Drawable{
    private Point middle;
    private int radius;
    private PApplet parent;
    private int rgb;

    public Circle(PApplet parent, Point middle, int radius) {
        this.middle = middle;
        this.radius = radius;
        this.parent = parent;
        this.rgb = 120;
    }

    public Circle(PApplet parent, Point middle, int radius, int rgb) {
        this.middle = middle;
        this.radius = radius;
        this.parent = parent;
        this.rgb = rgb;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public Point getMiddle() {
        return middle;
    }

    public void setMiddle(Point middle) {
        this.middle = middle;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void draw(){
        parent.fill(rgb);
        parent.ellipse(middle.getX(), middle.getY(), 2*radius, 2*radius);
    }
}
