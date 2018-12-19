package com.graphs.test;

import com.graphs.lib.graph.LayerChart;
import com.graphs.lib.graph.LineChart;
import com.graphs.lib.graph.PointChart;
import com.graphs.lib.graph.element.Color;
import com.graphs.lib.graph.element.Point;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //TODO:  dla y = 5 i 7 tylko zle rysuje
        LayerChart pointChart = new LayerChart(1280, 720);
        pointChart.addGraphData("data1",Arrays.asList(
                new Point(3, 7),
                new Point(-2, 5),
                new Point(1, 7),
                new Point(-7, 5)
        ));
//        pointChart.addGraphData("data2",Arrays.asList(
//                new Point(3, 7),
//                new Point(0, -2),
//                new Point(1,5),
//                new Point(-4,-2),
//                new Point(3,2)
//        ), new Color(34,12,157));
        pointChart.setTitle("My new title", 48);
        pointChart.show();
    }
}