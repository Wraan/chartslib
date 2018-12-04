package com.graphs.test;

import com.graphs.lib.graph.HorizontalColumnChart;
import com.graphs.lib.graph.PointGraph;
import com.graphs.lib.graph.VerticalColumnChart;

public class Main {
    public static void main(String[] args) {
        //PointGraph graph = new PointGraph(1280, 720);
        HorizontalColumnChart graph = new HorizontalColumnChart(1280,720);
        graph.run();

    }
}