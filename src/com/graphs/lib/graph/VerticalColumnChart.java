package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Rectangle;
import com.graphs.lib.graph.element.Text;

import java.util.ArrayList;
import java.util.List;

public class VerticalColumnChart extends ColumnChart
{
    private List<Rectangle> columns = new ArrayList<>();
    private List<Text> columnsType = new ArrayList<>();
    private int horizontalAxisValues = 5;

    public VerticalColumnChart(int width, int height) {super(width, height); }

    public VerticalColumnChart()
    {
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw()
    {

    }
}
