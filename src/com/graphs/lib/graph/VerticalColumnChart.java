package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Rectangle;
import com.graphs.lib.graph.element.Text;

import java.util.ArrayList;
import java.util.List;

public class VerticalColumnChart extends ColumnChart
{
    public VerticalColumnChart(int width, int height)
    {
        super(width, height);
    }

    public VerticalColumnChart()
    {
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw()
    {
        drawEmptyChart();
        drawHorizontalSeparatorsWithLabels();
        drawVerticalColumns();
        drawVerticalColumnsLabels();
    }

    private void drawHorizontalSeparatorsWithLabels()
    {

    }

    private void drawVerticalColumns()
    {

    }

    private void drawVerticalColumnsLabels()
    {

    }
}
