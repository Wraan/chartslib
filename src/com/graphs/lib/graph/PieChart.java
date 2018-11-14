package com.graphs.lib.graph;

import com.graphs.lib.graph.element.*;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PieChart extends Graph {

    private List<GraphData<Double>> values = new ArrayList<>();
    private List<GraphData<Double>> ratios = new ArrayList<>();
    private List<Arc> arcs = new ArrayList<>();
    private List<Rectangle> legendRectangle = new ArrayList<>();
    private List<Text> legendText = new ArrayList<>();
    private Boolean isLegendEnabled = false;
    private String title = "";

    public PieChart(){
    }

    public PieChart(List<Double> values){
        createValuesList(values);
    }

    public PieChart(int width, int height,List<String> labels, List<Double> values) throws Exception {
        super(width, height);
        if(width< 300)
            throw new Exception("Window width must be at least 300");
        if(labels.size() != values.size())
            throw new Exception("Number of labels is different than number of values");
        createValuesList(labels,values);
    }

    public PieChart(List<String> labels, List<Double> values) throws Exception {
        if(labels.size() != values.size())
            throw new Exception("Number of labels is different than number of values");
        createValuesList(labels,values);
    }

    public PieChart(int width, int height, List<Double> values) throws Exception {
        super(width, height);
        if(width< 300)
            throw new Exception("Window width must be at least 300");
        createValuesList(values);
    }

    private void createValuesList(List<String> labels, List<Double> values){
        for(int i = 0; i<values.size(); i++)
            this.values.add(new GraphData<>(labels.get(i), values.get(i)));
    }
    private void createValuesList(List<Double> values){
        for(int i = 0; i<values.size(); i++)
            this.values.add(new GraphData<>("Null", values.get(i)));
    }
    private void countRatiosOfSeries(){
        List<Double> ratios= new ArrayList<>();
        double sum = 0;

        for (GraphData<Double> element : values) {
            sum += element.getData();
        }
        for (GraphData<Double> element : values) {
            ratios.add(element.getData()/sum);
        }

        for(int i = 0; i<values.size(); i++){
            this.ratios.add(new GraphData<>(values.get(i).getLabel(),ratios.get(i)));
        }
    }
    private void createArcs(){
        //Todo specific colors
        List<Arc> createdArcs = new ArrayList<>();
        float sum = 0;
        int randomNum1,randomNum2,randomNum3;
        int minimum = min(width,height);
        Point center;
        int radius;
        if(isLegendEnabled){
            center = new Point(0.35*width,0.5*height);
            radius = (int)(0.6 * minimum);
        }
        else{
            center = new Point(0.5*width,0.5*height);
            radius = (int)(0.80 * minimum);
        }

        for(int i = 0; i < ratios.size();i++){
            randomNum1 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum2 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum3 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            createdArcs.add(new Arc(this,center,radius,
                    sum * 2 * PConstants.PI,(float)((sum + ratios.get(i).getData()) * PConstants.PI*2),new Color(randomNum1,randomNum2,randomNum3),new Color(0,0,0),0,1));
            sum+=ratios.get(i).getData();
        }
        this.arcs = createdArcs;
    }
    private void drawArcs(){
        for (Arc arc: arcs) {
            arc.draw();
        }
    }
    private void createLegend(){
        //To do colors matching chart
        double ratio = 0.9 / values.size();
        double sum = 0.1;
        int randomNum1,randomNum2,randomNum3;
        for(int i = 0; i<values.size();i++){
            randomNum1 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum2 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum3 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            legendRectangle.add( new Rectangle(this,new Point(0.67*width,(sum*height)),new Point(0.67*width + min(width,height)*0.03 ,sum*height + min(width,height)*0.03),new Color(randomNum1,randomNum2,randomNum3)));
            legendText.add(new Text(this,"kappa",new Point(0.67*width + min(width,height)*0.04,(sum*height)),new Point(0.98*width,sum*height + min(width,height)*0.03),0.025f*min(width,height),new Color(0,0,0)));
            sum += ratio;
        }
    }
    private void drawLegend(){
        for (Rectangle r: legendRectangle) {
            r.draw();
        }
        for (Text t: legendText) {
            t.draw();
        }
    }
    private void createChart(){
        countRatiosOfSeries();
        createArcs();
        drawArcs();
        if(isLegendEnabled){
            createLegend();
            drawLegend();
        }
        drawTitle();
    }
    private void drawTitle(){
            Text text =new Text(this,title,new Point(0.1*width,0.02*height),new Point(0.9*width,0.1 * height),0.05f*min(width,height),new Color(0,0,0));
            //Todo: Set align
            text.draw();
    }
    public void enableLegend(){
        isLegendEnabled = true;
    }
    public void disableLegend(){
        isLegendEnabled = false;
    }
    public void setTitle(String title){
        this.title = title;
    }


    @Override
    public void draw(){
        createChart();
        noLoop();
    }
}
