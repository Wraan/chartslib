package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Color;
import com.graphs.lib.graph.element.GraphTitle;
import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.Text;
import processing.core.PApplet;

abstract class Graph extends PApplet {

    private GraphTitle title;

    Graph() {
        this.width = 800;
        this.height = 600;
    }
    Graph(int width, int height) {
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

    public void setTitle(String title, float fontsize, String align, Color color){
        this.title = new GraphTitle(this,title,fontsize,align,color);
    }

    void drawTitle(){
        //Todo: Exception
        if(title != null)
            title.draw();
    }
}
