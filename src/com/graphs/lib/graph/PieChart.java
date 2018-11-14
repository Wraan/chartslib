package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Arc;
import com.graphs.lib.graph.element.Color;
import com.graphs.lib.graph.element.Point;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PieChart extends Graph {

    private List<GraphData<Double>> values = new ArrayList<>();
    private List<GraphData<Double>> ratios = new ArrayList<>();
    private List<Arc> arcs;

    public PieChart(){
    }

    public PieChart(int width, int height){
        super(width, height);
    }

    public PieChart(List<String> labels, List<Double> values) throws Exception {
        if(labels.size() != values.size())
            throw new Exception("Number of labels is different than number of values");
        createValuesList(labels,values);
        countRatios();
        createArcs();

    }

    public PieChart(int width, int height, List<Double> values) {
        super(width, height);

    }

    private void createValuesList(List<String> labels, List<Double> values){
        for(int i = 0; i<labels.size(); i++)
            this.values.add(new GraphData<Double>(labels.get(i), values.get(i)));
    }

    private void countRatios()
    {
        List<Double> ratios= new ArrayList<>();
        double sum = 0;

        for (GraphData<Double> element : values) {
            sum += element.getData();
        }
        for (GraphData<Double> element : values) {
            ratios.add(element.getData()/sum);
        }
        for (double value:ratios) {
            System.out.println(value);
        }

        for(int i = 0; i<values.size(); i++){
            this.ratios.add(new GraphData<>(values.get(i).getLabel(),ratios.get(i)));
        }
    }

    private void createArcs()
    {
        List<Arc> createdArcs = new ArrayList<>();
        float sum = 0;
        int randomNum1,randomNum2,randomNum3;
        int minimum = min(width,height);
        for(int i = 0; i < ratios.size();i++){
            randomNum1 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum2 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum3 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            createdArcs.add(new Arc(this,new Point((int)(0.35*width),(int)(0.5*height)),(int)(0.6 * minimum),
                    sum * 2 * PConstants.PI,(float)((sum + ratios.get(i).getData()) * PConstants.PI*2),new Color(randomNum1,randomNum2,randomNum3),new Color(0,0,0),0,1));
            sum+=ratios.get(i).getData();
        }
        this.arcs = createdArcs;
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
