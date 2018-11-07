package com.graphs.test;

import com.graphs.lib.graph.LukTest;
import com.graphs.lib.graph.PieChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> values = new ArrayList<>(Arrays.asList(2.5,2.5,2.5,2.5,2.5,2.5,2.5,100.0,2.5,2.5,2.5,2.5,2.5,2.5,2.5));
        PieChart pie = new PieChart(400,800,values);
        pie.run();
    }
}
