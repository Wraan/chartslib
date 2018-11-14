package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class Text implements Drawable {

    private String text;
    private float fontSize;
    private Color fontColor;
    private Point leftUp;
    private Point rightDown;
    private PApplet parent;

    Text(PApplet parent, String text, Rectangle area) {
        this(parent, text, area.getLeftUp(), area.getRightDown());
    }

    Text(PApplet parent, String text, Point leftUp, Point rightDown)
    {
        this.parent = parent;
        this.setText(text);
        this.setLeftUp(leftUp);
        this.setRightDown(rightDown);
        this.setFontSize(8);
        this.setFontColor(new Color(255,255,255));
    }

    Text(PApplet parent, String text, Point leftUp)
    {
        this(parent, text, leftUp, null);
    }




    @Override
    public void draw() {
        parent.textSize(fontSize);
        parent.fill(fontColor.getR(), fontColor.getG(), fontColor.getB());
        parent.text(text, getLeftUp().getX(), getLeftUp().getY(), getRightDown().getX(), getRightDown().getY());
        parent.textSize(8);
        parent.fill(255,255,255);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
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
}
