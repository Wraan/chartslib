package com.chartslib.chart;

import com.chartslib.data.PointData;
import com.chartslib.element.Point;
import com.chartslib.element.PointChartArea;
import com.chartslib.element.PointChartAreaSettings;
import com.chartslib.exceptions.InvalidWindowSizeException;

import java.util.List;

public class LayerChart extends PointChart {
    public LayerChart(int width, int height) throws InvalidWindowSizeException {
        super(width, height);
    }
    public LayerChart(){
        super();
    }

    @Override
    protected void drawGraph(Point start, Point end, List<PointData> graphData, PointChartAreaSettings pointChartAreaSettings) {
        PointChartArea pointChartArea = new PointChartArea(this, start, end, graphData, pointChartAreaSettings);
        pointChartArea.setType(PointChartArea.Type.LayerChart);
        pointChartArea.draw();
    }
}
