package com.chartslib.chart;

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
