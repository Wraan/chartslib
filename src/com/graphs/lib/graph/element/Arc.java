package com.graphs.lib.graph.element;

import processing.core.PApplet;
import processing.core.PConstants;

public class Arc implements Drawable{
    private Point center;
    private float radius;
    private float start;
    private float stop;
    private int mode = 3;
    private PApplet parent;
    private Color color = new Color(0,0,0);
    private Color outColor = new Color(0,0,0);
    private int thickness = 1;

    public Arc(PApplet parent,Point center, double radius, float start, float stop, Color color, Color outColor, int mode, int thickness) {
        this.center = center;
        this.radius = (float)radius;
        this.start = start;
        this.stop = stop;
        this.mode = mode;
        this.parent = parent;
        this.color = color;
        this.outColor = outColor;
        this.thickness = thickness;
    }
    public Arc(Point center, double radius, float start, float stop, PApplet parent) {
        this.center = center;
        this.radius = (float)radius;
        this.start = start;
        this.stop = stop;
        this.parent = parent;
    }
    public Arc(Point center, double radius, float start, float stop, PApplet parent, Color color, int mode) {
        this.center = center;
        this.radius = (float)radius;
        this.start = start;
        this.stop = stop;
        this.parent = parent;
        this.color = color;
        this.mode = mode;
    }
    public Arc(Point center, int radius, float start, float stop, PApplet parent, Color color) {
        this.center = center;
        this.radius = radius;
        this.start = start;
        this.stop = stop;
        this.parent = parent;
        this.color = color;
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
        parent.arc(center.getX(),center.getY(),(float)radius,(float)radius,start,stop,mode);
    }
}
