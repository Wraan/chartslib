package com.graphs.lib.graph;

import com.graphs.lib.graph.element.*;
import processing.core.PConstants;

public class LukTest extends Graph {

    public LukTest(int width, int height){
        super(width, height);
    }
    public LukTest(){
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw(){
        Arc arc1 = new Arc(new Point(100,100),100, PConstants.PI,2*PConstants.PI,3,this,
                new Color(0,255,0),new Color(0,0,0),1);
        arc1.draw();

    }





}
