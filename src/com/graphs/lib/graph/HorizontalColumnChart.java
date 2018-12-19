package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Rectangle;
import com.graphs.lib.graph.element.Text;
import com.graphs.lib.graph.exceptions.InvalidWindowSizeException;

import java.util.ArrayList;
import java.util.List;

public class HorizontalColumnChart extends ColumnChart
{
    public HorizontalColumnChart(int width, int height) throws InvalidWindowSizeException {
        super(width, height);
    }

    public HorizontalColumnChart()
    {
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw()
    {
        drawEmptyChart();
        drawVerticalSeparatorsWithLabels();
        drawHorizontalColumns();
        drawHorizontalColumnsLabels();
    }

    @Override
    protected void createChart() {

    }

    private void drawVerticalSeparatorsWithLabels()
    {

    }

    private void drawHorizontalColumns()
    {

    }

    private void drawHorizontalColumnsLabels()
    {

    }
}
