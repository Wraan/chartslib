package com.graphs.test;

import com.graphs.lib.graph.HorizontalColumnChart;
import com.graphs.lib.graph.PointGraph;
import com.graphs.lib.graph.VerticalColumnChart;

public class Main {
    public static void main(String[] args) {
        //PointGraph graph = new PointGraph(1280, 720);
        VerticalColumnChart graph = new VerticalColumnChart(1280,720);
        graph.setHorizontalAxisRatio(0.7);
        graph.setVerticalAxisRatio(0.7);
        graph.run();
    }
}