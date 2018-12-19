package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Rectangle;
import com.graphs.lib.graph.element.Text;
import com.graphs.lib.graph.exceptions.InvalidWindowSizeException;

import java.util.ArrayList;
import java.util.List;

public class VerticalColumnChart extends ColumnChart
{
    public VerticalColumnChart(int width, int height) throws InvalidWindowSizeException {
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

    @Override
    protected void createChart() {

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
