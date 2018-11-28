package com.graphs.lib.graph;

import com.graphs.lib.graph.data.PieData;
import com.graphs.lib.graph.element.*;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PieChart extends Graph {

    private List<PieData> values = new ArrayList<>();
    private List<PieData> ratios = new ArrayList<>();
    private List<Rectangle> legendRectangle = new ArrayList<>();
    private List<Text> legendText = new ArrayList<>();
    protected Boolean isLegendEnabled = false;

    PieChart(){
    }

    public PieChart(int width, int height) throws Exception {
        super(width, height);
        if(width < 300) throw new Exception("Window width must be at least 300");
    }

    protected List<PieData> countRatiosOfSeries(List<PieData> values){
        List<Double> ratios = new ArrayList<>();
        List<PieData> chartRatios = new ArrayList<>();
        double sum = 0;

        for (PieData element : values) {
            sum += element.getData();
        }
        for (PieData element : values) {
            ratios.add(element.getData()/sum);
        }

        for(int i = 0; i<values.size(); i++){
            chartRatios.add(new PieData(values.get(i).getLabel(),ratios.get(i)));
        }
        return chartRatios;
    }
    protected void createArcs(double radius,List<PieData> ratios){
        //Todo specific colors
        List<Arc> arcs = new ArrayList<>();
        float sum = 0;
        int randomNum1,randomNum2,randomNum3;
        Point center;
        if(isLegendEnabled)
            center = new Point(0.35*width,0.5*height);
        else
            center = new Point(0.5*width,0.5*height);

        for(int i = 0; i < ratios.size();i++){
            randomNum1 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum2 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum3 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            arcs.add(new Arc(this,center,radius,
                    sum * 2 * PConstants.PI,(float)((sum + ratios.get(i).getData()) * PConstants.PI*2),new Color(randomNum1,randomNum2,randomNum3),new Color(0,0,0),0,1));
            sum+=ratios.get(i).getData();

        }
        for (Arc arc: arcs) {
            arc.draw();
        }
    }
    protected void createLegend(){
        //To do colors matching chart
        double ratio = 0.9 / values.size();
        double sum = 0.1;
        int randomNum1,randomNum2,randomNum3;
        for(int i = 0; i<values.size();i++){
            randomNum1 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum2 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            randomNum3 = ThreadLocalRandom.current().nextInt(0, 255 + 1);
            legendRectangle.add( new Rectangle(this,new Point(0.67*width,(sum*height)),new Point(0.67*width + min(width,height)*0.03 ,sum*height + min(width,height)*0.03),new Color(randomNum1,randomNum2,randomNum3)));
            legendText.add(new Text(this,values.get(i).getLabel(),new Point(0.67*width + min(width,height)*0.04,(sum*height)),new Point(0.98*width,sum*height + min(width,height)*0.03),0.025f*min(width,height),new Color(0,0,0)));
            sum += ratio;
        }
    }
    protected void drawLegend(){
        for (Rectangle r: legendRectangle) {
            r.draw();
        }
        for (Text t: legendText) {
            t.draw();
        }
    }
    private void createChart(){
        // Todo appropriate colors
        int minimum = min(width,height);
        int radius;
        if(values.size() == 0)
            insertData("Null",2.0);
        this.ratios = countRatiosOfSeries(this.values);
        if(isLegendEnabled){
            radius = (int)(0.6 * minimum);
            createLegend();
            drawLegend();
        }
        else
            radius = (int)(0.80 * minimum);
        createArcs(radius,ratios);
        drawTitle();
    }


    public void insertData(List<PieData> data){
        this.values = data;
    }
    public void insertData(String label,double data){
        PieData pieData = new PieData(label,data);
        this.values.add(pieData);
    }
    public void enableLegend(){
        isLegendEnabled = true;
    }
    public void disableLegend(){
        isLegendEnabled = false;
    }

    @Override
    public void draw(){
        createChart();
        noLoop();
    }
}
