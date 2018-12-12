package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class GraphTitle implements Drawable {
    private PApplet parent;
    private String title;
    private float fontsize;
    private Text.Align hAlign;
    private Text.Align vAlign;
    private Color color;

    public GraphTitle(PApplet parent, String title, float fontsize, Text.Align hAlign, Text.Align vAlign, Color color) {

        this.parent = parent;
        this.title = title;
        this.fontsize = fontsize;
        this.hAlign = hAlign;
        this.vAlign = vAlign;
        this.color = color;
    }
    public void draw(){
        Text text = new Text(parent, title, new Point(0,0),new Point(parent.width,0.1*parent.height), fontsize, color);
        text.setHorizontalAlign(hAlign);
        text.setVerticalAlign(vAlign);
        text.draw();
    }

    public GraphTitle(PApplet parent, String title) {
        this(parent, title, 30.0f, Text.Align.CENTER, Text.Align.CENTER, new Color(0,0,0));
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

    public void setFontsize(float fontsize) {
        this.fontsize = fontsize;
    }

    public void sethAlign(Text.Align hAlign) {
        this.hAlign = hAlign;
    }

    public void setvAlign(Text.Align vAlign) {
        this.vAlign = vAlign;
    }
}
