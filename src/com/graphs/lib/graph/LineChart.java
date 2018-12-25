package com.graphs.lib.graph;

import com.graphs.lib.graph.PointChart;
import com.graphs.lib.graph.data.PointData;
import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.PointChartArea;
import com.graphs.lib.graph.element.PointChartAreaSettings;
import com.graphs.lib.graph.exceptions.InvalidWindowSizeException;

import java.util.List;

public class LineChart extends PointChart {
    public LineChart(int width, int height) throws InvalidWindowSizeException {
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
