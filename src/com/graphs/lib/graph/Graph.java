package com.graphs.lib.graph;

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
    public abstract void draw();
}
