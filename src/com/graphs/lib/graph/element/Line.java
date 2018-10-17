package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Line implements Drawable{
    private int thickness;
    private Point start;
    private Point end;
    private Color color;
    private PApplet parent;

    public Line(PApplet parent, Point start, Point end, int thickness, Color color) {
        this.thickness = thickness;
        this.start = start;
        this.end = end;
        this.color = color;
        this.parent = parent;
    }
    public Line(PApplet parent, Point start, Point end, int thickness) {
        this.thickness = thickness;
        this.start = start;
        this.end = end;
        this.color = new Color(0,0,0);
        this.parent = parent;
    }

    public Line(PApplet parent, Point start, Point end) {
        this.thickness = 1;
        this.start = start;
        this.end = end;
        this.color = new Color(0,0,0);
        this.parent = parent;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void draw() {
        parent.stroke(color.getR(), color.getG(), color.getB());
        parent.strokeWeight(thickness);
        parent.line(start.getX(), start.getY(), end.getX(), end.getY());

    }
}
