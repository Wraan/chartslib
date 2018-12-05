package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Color;
import com.graphs.lib.graph.element.GraphTitle;
import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.Text;
import processing.core.PApplet;

abstract class Graph extends PApplet {

    private GraphTitle title;
    protected Boolean isLegendEnabled = true;
    protected Boolean isTitileEnabled = true;
    protected Color backgroundColor = new Color(204, 204, 204);

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

    public void setTitle(String title, float fontsize, Text.Align align, Color color){
        this.title = new GraphTitle(this,title,fontsize,align,color);
    }

    public void setTitle(String title) {
        this.title = new GraphTitle(this, title);
    }

    void drawTitle(){
        //Todo: Exception
        if(title != null)
            title.draw();
    }

    public void enableLegend(){
        isLegendEnabled = true;
    }

    public void disableLegend(){
        isLegendEnabled = false;
    }

    public void enableTitile(){
        isTitileEnabled = true;
    }

    public void disableTitile(){
        isTitileEnabled = false;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

}
