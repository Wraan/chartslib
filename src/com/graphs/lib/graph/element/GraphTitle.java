package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class GraphTitle implements Drawable {
    private PApplet parent;
    private String title;
    private float fontsize;
    private String align;
    private Color color;


    public GraphTitle() {
    }

    public GraphTitle(PApplet parent, String title, float fontsize, String align, Color color) {
        this.parent = parent;
        this.title = title;
        this.fontsize = fontsize;
        this.align = align;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getFontsize() {
        return fontsize;
    }

    public void setFontsize(float fontsize) {
        this.fontsize = fontsize;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void draw(){
        Text text = new Text(parent, title, new Point(0,0),new Point(parent.width,0.1*parent.height),fontsize,color);
        //text.setHorizontalAlign(align);
        text.draw();
    }
}
