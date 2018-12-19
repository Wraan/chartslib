package com.graphs.test;

import com.graphs.LineChart;
import com.graphs.lib.graph.PointChart;
import com.graphs.lib.graph.element.Color;
import com.graphs.lib.graph.element.Point;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LineChart pointChart = new LineChart(1280, 720);
        pointChart.addGraphData("data1",Arrays.asList(
                new Point(-2, 5),
                new Point(-1, -5),
                new Point(0,7),
                new Point(1,-2),
                new Point(2,2)
        ));
        pointChart.addGraphData("data2",Arrays.asList(
                new Point(3, 7),
                new Point(0, -2),
                new Point(1,5),
                new Point(-4,-2),
                new Point(3,2)
        ), new Color(34,12,157));
        pointChart.setTitle("My new title", 48);
        pointChart.run();
    }
}