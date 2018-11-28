package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Circle implements Drawable{
    private Point middle;
    private float radius;
    private PApplet parent;
    private Color color = new Color(0,0,0);
    private Color outColor = new Color(0,0,0);
    private int thickness = 1;
    private boolean isFill = true;

    public Circle(PApplet parent, Point middle, double radius) {
        this.middle = middle;
        this.radius = (float)radius;
        this.parent = parent;
    }

    public Circle(PApplet parent, Point middle, float radius) {
        this.middle = middle;
        this.radius = radius;
        this.parent = parent;
    }

    public Circle(PApplet parent, Point middle, double radius, Color color) {
        this.middle = middle;
        this.radius = (float)radius;
        this.parent = parent;
        this.color = color;
    }

    public Circle(PApplet parent, Point middle, float radius, Color color) {
        this.middle = middle;
        this.radius = radius;
        this.parent = parent;
        this.color = color;
    }
    public Circle(PApplet parent, Point middle, int radius, Color color, int thickness) {
        this.middle = middle;
        this.radius = radius;
        this.parent = parent;
        this.color = color;
        this.thickness = thickness;
    }

    public Circle(PApplet parent, Point middle, double radius, Color color, int thickness, Color outColor) {
        this.middle = middle;
        this.radius = (float)radius;
        this.parent = parent;
        this.color = color;
        this.thickness = thickness;
        this.outColor = outColor;
    }

    public Circle(PApplet parent, Point middle, float radius, Color color, int thickness, Color outColor) {
        this.middle = middle;
        this.radius = radius;
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

    public Point getMiddle() {
        return middle;
    }

    public void setMiddle(Point middle) {
        this.middle = middle;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void draw(){
        if(getIsFill())
            parent.fill(color.getR(), color.getG(), color.getB());
        else
            parent.noFill();
        parent.stroke(outColor.getR(), outColor.getG(), outColor.getB());
        parent.strokeWeight(thickness);
        parent.ellipse((int)middle.getX(), (int)middle.getY(), 2*radius, 2*radius);
    }

    public boolean getIsFill() {
        return isFill;
    }

    public void setIsFill(boolean isFill) {
        this.isFill = isFill;
    }
}