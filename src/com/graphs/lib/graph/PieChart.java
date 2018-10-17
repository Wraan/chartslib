package com.graphs.lib.graph;

import com.graphs.lib.graph.element.*;

public class PieChart extends Graph {

    public PieChart(int width, int height){
        super(width, height);
    }
    public PieChart(){
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw(){
        Line line1 = new Line(this, new Point(50,50), new Point(450,450), 3, new Color(25,130,47));
        line1.draw();
        Line line2 = new Line(this, new Point(250,50), new Point(50,250), 2, new Color(251,30,247));
        line2.draw();

        RegularPolygon rp = new RegularPolygon(this, new Point(500, 500), 200, 4);
        rp.rotate(PI/4);
        rp.draw();

    }





}
