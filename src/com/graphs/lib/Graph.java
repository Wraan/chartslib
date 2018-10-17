package com.graphs.lib;

import processing.core.PApplet;

abstract class Graph extends PApplet {

    protected Graph() {
        this.width = 600;
        this.height = 800;
    }
    protected Graph(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void run() {
        PApplet.runSketch(new String[] {Graph.class.getName()},this);
    }

    public void settings(){
    }

    public void setup() {
        surface.setResizable(false);
    }
    public void draw() {
        fill(240);
        ellipse((float)(0.4*width), (float)(0.55*height), (float)(0.7*height), (float)(0.7*height));
    }
}
