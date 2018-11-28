package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Rectangle implements Drawable {
    private Point leftUp;
    private Point rightDown;
    private PApplet parent;
    private Color color = new Color(0,0,0);
    private Color outColor = new Color(0,0,0);
    private int thickness = 1;

    public Rectangle(PApplet parent, Point leftUp, Point rightDown){
        this.parent = parent;
        this.leftUp = leftUp;
        this.rightDown = rightDown;
    }

    public Rectangle(PApplet parent, Point leftUp, Point rightDown, Color color){
        this.parent = parent;
        this.leftUp = leftUp;
        this.rightDown = rightDown;
        this.color = color;
    }

    public Rectangle(PApplet parent, Point leftUp, Point rightDown, Color color, int thickness, Color outColor){
        this.parent = parent;
        this.leftUp = leftUp;
        this.rightDown = rightDown;
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

    public Point getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Point leftUp) {
        this.leftUp = leftUp;
    }

    public Point getRightDown() {
        return rightDown;
    }

    public void setRightDown(Point rightDown) {
        this.rightDown = rightDown;
    }

    @Override
    public void draw() {
        parent.fill(color.getR(), color.getG(), color.getB());
        parent.stroke(outColor.getR(), outColor.getG(), outColor.getB());
        parent.strokeWeight(thickness);
        parent.rect(leftUp.getX(), leftUp.getY(), rightDown.getX()-leftUp.getX(), rightDown.getY()-leftUp.getY());
    }
}
