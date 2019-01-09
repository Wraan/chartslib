package com.chartslib.chart;

public class HorizontalColumnChart extends ColumnChart
{
    public HorizontalColumnChart(int width, int height)
    {
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
