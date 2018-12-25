package com.graphs.lib.graph;

import com.graphs.lib.graph.data.PieData;
import com.graphs.lib.graph.element.*;
import com.graphs.lib.graph.exceptions.InvalidDataException;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PieChart extends Graph {

    private List<PieData> values = new ArrayList<>();
    private List<PieData> ratios = new ArrayList<>();

    public PieChart(){
        super();
    }
    public PieChart(int width, int height) throws Exception {
        super(width, height);
    }

    List<PieData> countRatiosOfSeries(List<PieData> values){
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
            chartRatios.add(new PieData(values.get(i).getLabel(),ratios.get(i),values.get(i).getColor()));
        }
        return chartRatios;
    }
    void createArcs(double radius, List<PieData> ratios){
        List<Arc> arcs = new ArrayList<>();
        float sum = 0;
        Point center;
        if(isLegendEnabled)
            center = new Point(0.35*width,0.5*height);
        else
            center = new Point(0.5*width,0.5*height);

        for(int i = 0; i < ratios.size();i++){
            arcs.add(new Arc(this,center,radius,
                    (sum * 2 * PConstants.PI) - (PConstants.PI/2),(float)(((sum + ratios.get(i).getData()) * PConstants.PI*2)) - (PConstants.PI/2),ratios.get(i).getColor(),ColorsPalette.Black,0,1));
            sum+=ratios.get(i).getData();

        }
        for (Arc arc: arcs) {
            arc.draw();
        }
    }
    void drawLegend(List<PieData> pieData){
        List<LegendItem> legendItems = new ArrayList<>();
        for(PieData pd : pieData){
            legendItems.add(new LegendItem(pd.getLabel(),pd.getColor()));
        }
        LegendArea legendArea = new LegendArea(this,new Point(0.67*width,(0.15*height)),legendItems);
        legendArea.draw();
    }
    @Override
    protected void createChart() throws InvalidDataException {
        int minimum = min(width,height);
        int radius;
        if(values.size() == 0)
            insertData("Null",2.0);
        this.ratios = countRatiosOfSeries(this.values);
        if(isLegendEnabled){
            radius = (int)(0.6 * minimum);
            drawLegend(ratios);
        }
        else
            radius = (int)(0.80 * minimum);
        createArcs(radius,ratios);
        drawTitle();
    }
    public void insertData(List<PieData> data){
        for(int i = 0; i< data.size(); i++){
            if(data.get(i).getColor() == null)
                data.get(i).setColor(ColorsPalette.colorPallette.get(i%20));
        }
        this.values = data;
    }
    public void insertData(String label, double data){
        PieData pieData = new PieData(label, data, ColorsPalette.colorPallette.get(values.size()%20));
        this.values.add(pieData);
    }
    public void insertData(String label, double data, Color color){
        PieData pieData = new PieData(label, data, color);
        this.values.add(pieData);
    }
    public void enableLegend(){
        isLegendEnabled = true;
    }
    public void disableLegend(){
        isLegendEnabled = false;
    }
}
