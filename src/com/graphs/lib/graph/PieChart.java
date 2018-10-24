package com.graphs.lib.graph;

import com.graphs.lib.graph.element.*;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends Graph {

    private List<Double> values;
    private List<Double> ratios;

    public PieChart(){
    }

    public PieChart(int width, int height){
        super(width, height);
    }

    public PieChart(List<Double> values) {
        this.values = values;
        ratios = countRatio(values);
    }

    public PieChart(int width, int height, List<Double> values) {
        super(width, height);
        this.values = values;
    }

    private List<Double> countRatio(List<Double> values)
    {
        List<Double> ratios= new ArrayList<>();
        double sum = 0;

        for (double value:values) {
            sum += value;
        }
        for (double value:values) {
            ratios.add(value/sum);
        }
        for (double value:ratios) {
            System.out.println(value);
        }

        return ratios;
    }

    private void createArcs(List<Double> ratios)
    {
        List<Arc> arcs = new ArrayList<>();
    }

    @Override
    public void draw(){

    }





}
