package com.graphs.test;

import com.graphs.lib.graph.LukTest;
import com.graphs.lib.graph.PieChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> values = new ArrayList<>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0));
        PieChart pie = new PieChart(values);
        pie.run();
    }
}
