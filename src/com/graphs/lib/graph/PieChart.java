package com.graphs.lib.graph;

import com.graphs.lib.graph.element.*;

public class PieChart extends Graph {

    public PieChart(int width, int height){
        super(width, height);
    }
    public PieChart(){
        super(900, 600);
    }

    @Override
    public void draw(){
        Rectangle rectangle1 = new Rectangle(this, new Point(50, 50), new Point(250, 250), new Color(251,130,27));
        rectangle1.setThickness(10);
        rectangle1.draw();
        Rectangle rectangle2 = new Rectangle(this, new Point(250, 250), new Point(450, 450), new Color(51,30,247));
        rectangle2.draw();
        Line line1 = new Line(this, new Point(50,50), new Point(450,450), 3, new Color(25,130,47));
        line1.draw();
        Line line2 = new Line(this, new Point(250,50), new Point(50,250), 2, new Color(251,30,247));
        line2.draw();
        Circle circle = new Circle(this, new Point(500, 100), 70);
        circle.setColor(new Color(230, 230, 0));
        circle.setThickness(6);
        circle.setOutColor(new Color(0,230,230));
        circle.setRadius(100);
        circle.setMiddle(new Point(700, 300));
        circle.draw();

    }





}
