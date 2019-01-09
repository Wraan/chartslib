package com.chartslib.chart;

import com.chartslib.element.Line;
import com.chartslib.element.Point;
import com.chartslib.element.Rectangle;
import com.chartslib.element.Text;

import java.util.ArrayList;
import java.util.List;

abstract class ColumnChart extends Chart
{
    private double verticalAxis;
    private double horizontalAxis;
    private double verticalAxisRatio = 1;
    private double horizontalAxisRatio = 1;
    private double minDataValues = 0; // can not be less than the smallest value of columnsValues List
    private double maxDataValues; // can not be bigger than the biggest value of columnsValues List
    private int valuesAmount = 2; // min. 2

    private double columnsWidth = 25; //TODO: think about proper axis scalling
    private double columnsGapsWidth = 3; //TODO: think about proper axis scalling
    //numbers of items from every list must be the same
    private List<Rectangle> columns = new ArrayList<>();
    private List<Text> columnsLabels = new ArrayList<>();
    private List<Text> columnsValues = new ArrayList<>();

    private List<Rectangle> legendRectangles = new ArrayList<>();
    private List<Text> legendLabels = new ArrayList<>();

    public void setVerticalAxisRatio(Double verticalAxisRatio)
    {
        this.verticalAxisRatio = verticalAxisRatio;
    }

    public void setHorizontalAxisRatio(Double horizontalAxisRatio)
    {
        this.horizontalAxisRatio = horizontalAxisRatio;
    }

    public ColumnChart(int width, int height)
    {
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
        drawLegend();
    }

    public void drawEmptyChart()
    {
        drawVerticalGraphLine();
        drawHorizontalGraphLine();
    }

    private void drawVerticalGraphLine()
    {
        verticalAxis = 0.05*width;

        Line line = new Line(this, new Point(verticalAxis, 0.95*height), //(36,684)
                new Point(verticalAxis, height-(verticalAxisRatio*0.85*height + 0.05*height))); //(36,72)
        line.draw();
    }

    private void drawHorizontalGraphLine()
    {
        horizontalAxis = 0.95*height;

        Line line = new Line(this, new Point(0.05*width, horizontalAxis), //(36,684)
                new Point(horizontalAxisRatio*0.85*width + 0.05*width, horizontalAxis)); //(1152,684)
        line.draw();
    }

    private void drawLegend()
    {

    }
}
