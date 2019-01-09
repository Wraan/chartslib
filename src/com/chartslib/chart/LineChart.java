package com.chartslib.chart;

import com.chartslib.data.PointData;
import com.chartslib.element.Point;
import com.chartslib.element.PointChartArea;
import com.chartslib.element.PointChartAreaSettings;

import java.util.List;

public class LineChart extends PointChart {
    public LineChart(int width, int height){
        super(width, height);
    }
    public LineChart(){
        super();
    }

    @Override
    protected void drawGraph(Point start, Point end, List<PointData> graphData, PointChartAreaSettings pointChartAreaSettings) {
        PointChartArea pointChartArea = new PointChartArea(this, start, end, graphData, pointChartAreaSettings);
        pointChartArea.setType(PointChartArea.Type.LineChart);
        pointChartArea.draw();
    }
}
