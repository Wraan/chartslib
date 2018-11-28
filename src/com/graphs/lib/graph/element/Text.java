package com.graphs.lib.graph.element;

import processing.core.PApplet;
import processing.core.PConstants;

import java.util.Arrays;

public class Text implements Drawable {

    private String text;
    private float fontSize;
    private Color fontColor;
    private Point leftUp;
    private Point rightDown;
    private PApplet parent;

    private int alignV = 3; //center
    private int alignH = 37; //left

    public void setHorizontalAlign(Align align) {
        if (Arrays.asList(Align.CENTER, Align.LEFT, Align.RIGHT).contains(align))
            this.alignH = align.getValue();
        else
            this.alignH = Align.CENTER.getValue();
    }

    public void setVerticalAlign(Align align) {
        if (Arrays.asList(Align.CENTER, Align.TOP, Align.BOTTOM).contains(align))
            this.alignV = align.getValue();
        else
            this.alignV = Align.CENTER.getValue();
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
    public Text(PApplet parent, String text, Point leftUp, Point rightDown, float fontSize,Color color)
    {
        this.parent = parent;
        this.setText(text);
        this.setLeftUp(leftUp);
        this.setRightDown(rightDown);
        this.setFontSize(fontSize);
        this.setFontColor(color);
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
        //parent.textSize(8);
        //parent.fill(255,255,255);
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

    public enum Align{
        CENTER(3), TOP(101),BOTTOM(102),LEFT(37),RIGHT(39);

        private final int value;
        Align(int value){ this.value = value;}

        public int getValue() {
            return value;
        }
    }
}
