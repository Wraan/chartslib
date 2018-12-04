package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Line;
import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.Rectangle;
import com.graphs.lib.graph.element.Text;

import java.util.ArrayList;
import java.util.List;

abstract class ColumnChart extends Graph
{
    private String title = "";
    private double verticalAxis;
    private double horizontalAxis;

    private double lowestH;
    private double highestH;
    private double lowestV;
    private double highestV;

    private double verticalRatio;
    private double horizonalRatio;

    public ColumnChart(int width, int height){
        super(width, height);
    }

    public ColumnChart()
    {
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw()
    {
        drawEmptyChart();
    }

    public void drawTitle()
    {
        Rectangle textArea = new Rectangle(this, new Point(0, 0), new Point(width, height));
        Text text = new Text(this, title, textArea);
        text.setFontSize(30);
    }

    public void drawEmptyChart()
    {
        drawVerticalGraphLine();
        drawHorizontalGraphLine();
    }

    private void drawHorizontalGraphLine() {
        if(highestV == 0 && lowestV == 0)
            horizontalAxis = 0.55*height;
        else if(highestV > 0 && lowestV > 0)
            horizontalAxis = 0.9*height;
        else if(highestV < 0 && lowestV < 0)
            horizontalAxis = 0.2*height;
        else{
            horizontalAxis = 0.2*height + (verticalRatio * highestV);
        }
        Line line = new Line(this, new Point(0.08*width, horizontalAxis),
                new Point(0.82*width, horizontalAxis));
        line.draw();
    }

    private void drawVerticalGraphLine(){
        if(highestH == 0 && lowestH == 0)
            verticalAxis = 0.45*width;
        else if(highestH > 0 && lowestH > 0)
            verticalAxis = 0.1*width;
        else if(highestH < 0 && lowestH < 0)
            verticalAxis = 0.8*width;
        else {
            verticalAxis = 0.1 * width + (horizonalRatio * -lowestH);
        }
        Line line = new Line(this, new Point(verticalAxis, 0.18*height),
                new Point(verticalAxis, 0.92*height));
        line.draw();
    }
}
