package com.graphs.lib.graph.element;

import processing.core.PApplet;
import processing.core.PConstants;

public class Arc implements Drawable{

    private Point center;
    private int radius;
    private float start;
    private float stop;
    private int mode;
    private PApplet parent;
    private Color color;
    private Color outColor;
    private int thickness;

    public Arc(Point center, int radius, float start, float stop, int mode, PApplet parent, Color color, Color outColor, int thickness) {
        this.center = center;
        this.radius = radius;
        this.start = start;
        this.stop = stop;
        this.mode = mode;
        this.parent = parent;
        this.color = color;
        this.outColor = outColor;
        this.thickness = thickness;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public double getStop() {
        return stop;
    }

    public void setStop(float stop) {
        this.stop = stop;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public PApplet getParent() {
        return parent;
    }

    public void setParent(PApplet parent) {
        this.parent = parent;
    }

    @Override
    public void draw() {
        parent.fill(color.getR(),color.getG(),color.getB());
        parent.stroke(outColor.getR(),outColor.getG(),outColor.getB());
        parent.strokeWeight(thickness);
        parent.arc(center.getX(),center.getY(),radius,radius,start,stop,mode);
    }
}