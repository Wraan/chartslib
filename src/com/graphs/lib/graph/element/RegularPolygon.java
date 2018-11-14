package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class RegularPolygon implements Drawable {
    private PApplet parent;
    private Point middle;
    private int radius;
    private Color color = new Color(0,0,0);
    private Color outColor = new Color(0,0,0);
    private int thickness = 1;
    private int n;
    private float rotation;
    private boolean isFill = true;

    public RegularPolygon(PApplet parent, Point middle, int radius, int n){
        this.parent = parent;
        this.middle = middle;
        this.radius = radius;
        this.n = n;
    }

    public RegularPolygon(PApplet parent, Point middle, int radius, int n, Color color){
        this.parent = parent;
        this.middle = middle;
        this.radius = radius;
        this.n = n;
        this.color = color;
    }
    public RegularPolygon(PApplet parent, Point middle, int radius, int n, Color color, int thickness, Color outColor){
        this.parent = parent;
        this.middle = middle;
        this.radius = radius;
        this.n = n;
        this.color = color;
        this.thickness = thickness;
        this.outColor = outColor;
    }

    public void rotate(float rotation) {
        this.rotation = rotation;
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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
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

    @Override
    public void draw() {
        if(getIsFill())
            parent.fill(color.getR(), color.getG(), color.getB());
        else
            parent.noFill();
        parent.stroke(outColor.getR(), outColor.getG(), outColor.getB());
        parent.strokeWeight(thickness);

        float angle = parent.TWO_PI / n;
        parent.beginShape();
        for (float a = 0; a < parent.TWO_PI; a += angle) {
            float sx = middle.getX() + PApplet.cos(a+rotation-parent.PI/2) * radius;
            float sy = middle.getY() + PApplet.sin(a+rotation-parent.PI/2) * radius;
            parent.vertex(sx, sy);
        }
        parent.endShape(parent.CLOSE);
    }

    public boolean getIsFill() {
        return isFill;
    }

    public void setIsFill(boolean isFill) {
        this.isFill = isFill;
    }
}