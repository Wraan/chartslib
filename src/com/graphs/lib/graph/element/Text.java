package com.graphs.lib.graph.element;

import processing.core.PApplet;
import processing.core.PConstants;

public class Text implements Drawable {

    private String text;
    private float fontSize;
    private Color fontColor;
    private Point leftUp;
    private Point rightDown;
    private PApplet parent;

    private int alignV = 3; //center
    private int alignH = 37; //left

    public void setHorizontalAlign(String CAPITAL_Align) {
        if(CAPITAL_Align.equals("CENTER"))
            this.alignH = 3;
        else if(CAPITAL_Align.equals("RIGHT"))
            this.alignH = 39;
        else
            this.alignH = 37; //left
    }

    public void setVerticalAlign(String CAPITAL_Align) {
        if(CAPITAL_Align.equals("BOTTOM"))
            this.alignV = 102;
        else if(CAPITAL_Align.equals("TOP"))
            this.alignV = 101;
        else
            this.alignV = 3; //center
    }



    public Text(PApplet parent, String text, Rectangle area) {
        this(parent, text, area.getLeftUp(), area.getRightDown());
    }

    public Text(PApplet parent, String text, Point leftUp, Point rightDown)
    {
        this.parent = parent;
        this.setText(text);
        this.setLeftUp(leftUp);
        this.setRightDown(rightDown);
        this.setFontSize(8);
        this.setFontColor(new Color(0,0,0));
    }

    public Text(PApplet parent, String text, Point leftUp)
    {
        this(parent, text, leftUp, null);
    }




    @Override
    public void draw() {
        parent.textSize(fontSize);
        parent.fill(fontColor.getR(), fontColor.getG(), fontColor.getB());
        parent.textAlign(alignH, alignV);
        if(rightDown != null)
            parent.text(text, getLeftUp().getX(), getLeftUp().getY(), getRightDown().getX()-getLeftUp().getX(), getRightDown().getY()-getLeftUp().getY());
        else
            parent.text(text, getLeftUp().getX(), getLeftUp().getY());
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
