package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Color;
import com.graphs.lib.graph.element.GraphTitle;
import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.Text;
import processing.core.PApplet;

abstract class Graph extends PApplet {


    private GraphTitle title = new GraphTitle(this, "no title", 24, Text.Align.TOP, Text.Align.CENTER, new Color(0,0,0));

    protected Boolean isLegendEnabled = true;
    protected Boolean isTitleEnabled = true;
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

    public void setTitle(String title, float fontsize, Text.Align vAlign, Text.Align hAlign, Color color){
        setTitle(title);
        setTitleFontSize(fontsize);
        setTitleVAlign(vAlign);
        setTitleHAlign(hAlign);
        setTitleColor(color);
    }
    public void setTitle(String title, float fontsize ){
        setTitle(title);
        setTitleFontSize(fontsize);
    }
    public void setTitle(String title, float fontsize, Text.Align vAlign, Text.Align hAlign){
        setTitle(title);
        setTitleFontSize(fontsize);
        setTitleVAlign(vAlign);
        setTitleHAlign(hAlign);
    }
    public void setTitle(String title){
        this.title.setTitle(title);
    }
    public void setTitleColor(Color color){
        this.title.setColor(color);
    }
    public void setTitleFontSize(float fontSize){
        this.title.setFontsize(fontSize);
    }
    public void setTitleVAlign(Text.Align vAlign){
        this.title.setvAlign(vAlign);
    }
    public void setTitleHAlign(Text.Align hAlign){
        this.title.sethAlign(hAlign);
    }

//    public void setTitle(String title) {
//        this.title = new GraphTitle(this, title);
//    }

    void drawTitle(){
        title.draw();
    }

    public void enableLegend(){
        isLegendEnabled = true;
    }

    public void disableLegend(){
        isLegendEnabled = false;
    }

    public void enableTitile(){
        isTitleEnabled = true;
    }

    public void disableTitle(){
        isTitleEnabled = false;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

}
