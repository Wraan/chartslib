package com.graphs.lib.element;

import processing.core.PApplet;

public class Circle {
    private int x;
    private int y;
    private int radius;
    private PApplet parent;

    public Circle(PApplet parent, int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public PApplet getParent() {
        return parent;
    }

    public void setParent(PApplet parent) {
        this.parent = parent;
    }

    public void draw(){
        parent.fill(145);
        parent.ellipse(x, y, 2*radius, 2*radius);
    }
}
