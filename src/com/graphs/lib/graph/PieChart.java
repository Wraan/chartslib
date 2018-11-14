package com.graphs.lib.graph;

import com.graphs.lib.graph.element.*;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class PieChart extends Graph {

    private List<Double> values;
    private List<Double> ratios;
    private List<Arc> arcs;

    public PieChart(){
    }

    public PieChart(int width, int height){
        super(width, height);
    }

    public PieChart(List<Double> values) {
        this.values = values;
        ratios = countRatio(values);
        this.arcs = createArcs(ratios);
    }

    public PieChart(int width, int height, List<Double> values) {
        super(width, height);
        this.values = values;
        ratios = countRatio(values);
        this.arcs = createArcs(ratios);
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

    private List<Arc> createArcs(List<Double> ratios)
    {
        List<Arc> arcs = new ArrayList<>();
        float sum = 0;
        int randomNum1,randomNum2,randomNum3;
        int minimum = min(width,height);
        for(int i = 0; i<ratios.size();i++){
            randomNum1 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum2 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum3 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            arcs.add(new Arc(this,new Point((int)(0.35*width),(int)(0.5*height)),(int)(0.6 * minimum),sum * 2 * PConstants.PI,(float)(sum+ratios.get(i))*PConstants.PI*2,new Color(randomNum1,randomNum2,randomNum3),new Color(0,0,0),0,1));
            sum+=ratios.get(i);
        }
        return arcs;
    }
    private void drawArcs(List<Arc> arcs){
        for (Arc arc: arcs) {
            arc.draw();
        }
    }

    @Override
    public void draw(){
        drawArcs(arcs);
    }





}
