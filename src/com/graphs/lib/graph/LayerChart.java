package com.graphs.lib.graph;

import com.graphs.lib.graph.PointChart;
import com.graphs.lib.graph.data.PointData;
import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.PointChartArea;
import com.graphs.lib.graph.element.PointChartAreaSettings;

import java.util.List;

public class LayerChart extends PointChart {
    public LayerChart(int width, int height){
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
