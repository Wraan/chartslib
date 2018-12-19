package com.graphs.test;

import com.graphs.lib.graph.RadarChart;

public class Main {
    public static void main(String[] args) {
        RadarChart radar = new RadarChart(800, 600);
        radar.setContour(RadarChart.Contour.Circle);
        double[] data = {1, 1, 2, 3, 4.000000000009, 5, 6, 7, 8, 10.000009};
        double[] data2 = {0.00001, 0.00002, 0.00003, 0.00004, 0.00005, 0.00006, 0.00007, 0.00008, 0.00009, 0.00005};
        String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M"};
        radar.addSeries(data, null);
//        radar.addSeries(data2, "");
        radar.setTitle("test");
        radar.setLabels(labels);
        radar.show();
    }
}