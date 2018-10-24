package com.graphs.lib.graph;

import com.graphs.lib.graph.element.*;
import processing.core.PConstants;

public class LukTest extends Graph {

    public LukTest(int width, int height){
        super(width, height);
    }
    public LukTest(){
    }

    @Override
    public void draw(){
//        Arc arc1 = new Arc(new Point(400,300),100, PConstants.PI,2*PConstants.PI,3,this,
//                new Color(0,255,0),new Color(0,0,0),1);
//        arc1.draw();
        Circle circle = new Circle(this, new Point((int)(0.4*this.width),(int)(0.5*this.height)),225,new Color(0,255,0));
        circle.draw();

    }





}
