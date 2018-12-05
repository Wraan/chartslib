package com.graphs.lib.graph.element;

import processing.core.PApplet;

public class GraphTitle implements Drawable {
    private PApplet parent;
    private String title;
    private float fontsize = 12;
    private Text.Align hAlign = Text.Align.CENTER;
    private Text.Align vAlign = Text.Align.TOP;
    private Color color;


    public GraphTitle(PApplet parent, String title) {
        this.parent = parent;
        this.title = title;
    }
    public GraphTitle(PApplet parent, String title, float fontsize) {
        this.parent = parent;
        this.title = title;
        this.fontsize = fontsize;
    }
    public GraphTitle(PApplet parent, String title, Text.Align hAlign, Text.Align vAlign) {
        this.parent = parent;
        this.title = title;
        this.hAlign = hAlign;
        this.vAlign = vAlign;
    }
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
