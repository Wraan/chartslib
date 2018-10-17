package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Rectangle implements Drawable {
    private Point leftUp;
    private Point rightDown;
    private PApplet parent;
    private int rgb;

    public Rectangle(PApplet parent, Point leftUp, Point rightDown){
        this.parent = parent;
        this.leftUp = leftUp;
        this.rightDown = rightDown;
        this.rgb = 120;
    }

    public Rectangle(PApplet parent, Point leftUp, Point rightDown, int rgb){
        this.parent = parent;
        this.leftUp = leftUp;
        this.rightDown = rightDown;
        this.rgb = rgb;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
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
        parent.fill(rgb);
        parent.stroke(0,0,0);
        parent.rect(leftUp.getX(), leftUp.getY(), rightDown.getX()-leftUp.getX(), rightDown.getY()-leftUp.getY());
    }
}
