package com.graphs.test;

import com.graphs.lib.graph.RadarChart;

public class Main {
    public static void main(String[] args) {
        RadarChart radar = new RadarChart(800, 600);
        radar.setContour(RadarChart.Contour.Polygon);
        double[] data = {1.0, 2.0, 3.0, 10, 11, 1, 1, 1, 1, 1, 1, 5};
        double[] data2 = {5.0, 4.0, 3.0, 2.0, 1.0, 1, 1, 1, 1, 1, 1, 1};
        String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M"};
        radar.addSeries(data, null);
        radar.addSeries(data2, "");
        radar.setTitle("test");
        radar.setLabels(labels);
        radar.show();
    }
}