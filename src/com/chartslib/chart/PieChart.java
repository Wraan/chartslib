package com.chartslib.chart;

import com.chartslib.data.PieData;
import com.chartslib.element.*;
import com.chartslib.exceptions.InvalidDataException;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends Chart {

    private List<PieData> values = new ArrayList<>();
    private List<PieData> ratios = new ArrayList<>();

    public PieChart(){
        super();
    }
    public PieChart(int width, int height) {
        super(width, height);
    }

    private void countRatiosOfSeries(){
        List<Double> calculatedRatios = new ArrayList<>();

        double sum = 0;

        for (PieData element : values)
            sum += element.getData();

        for (PieData element : values)
            calculatedRatios.add(element.getData()/sum);

        for(int i = 0; i<values.size(); i++)
            this.ratios.add(new PieData(values.get(i).getLabel(),calculatedRatios.get(i),values.get(i).getColor()));
    }
    private void createArcs(double radius){
        List<Arc> arcs = new ArrayList<>();
        float sum = 0;
        Point center;
        if(isLegendEnabled)
            center = new Point(0.35*width,0.5*height);
        else
            center = new Point(0.5*width,0.5*height);

        for(int i = 0; i < ratios.size();i++){
            arcs.add(new Arc(this,center,radius,
                    sum * 2 * PConstants.PI - (PConstants.PI/2),(float)(((sum + ratios.get(i).getData()) * PConstants.PI*2)) - (PConstants.PI/2),ratios.get(i).getColor(),ColorsPalette.Black,0,1));
            sum+=ratios.get(i).getData();

        }
        for (Arc arc: arcs) {
            arc.draw();
        }
    }
    private void drawLegend(){
        List<LegendItem> legendItems = new ArrayList<>();
        for(PieData pd : ratios){
            legendItems.add(new LegendItem(pd.getLabel(),pd.getColor()));
        }
        LegendArea legendArea = new LegendArea(this,new Point(0.67*width,(0.15*height)),legendItems);
        legendArea.setFontSize(legendFontSize);
        legendArea.draw();
    }
    @Override
    protected void createChart(){
        int minimum = min(width,height);
        int radius;
        if(values.size() == 0) {
            throw new InvalidDataException();
        }
        countRatiosOfSeries();
        if(isLegendEnabled){
            radius = (int)(0.75 * minimum);
            drawLegend();
        }
        else
            radius = (int)(0.80 * minimum);
        createArcs(radius);
    }
    public void insertData(String label, double data){
        PieData pieData = new PieData(label, data, ColorsPalette.colorPallette.get(values.size()%20));
        this.values.add(pieData);
    }
    public void insertData(String label, double data, Color color){
        PieData pieData = new PieData(label, data, color);
        this.values.add(pieData);
    }
    public void insertData(PieData data){
        this.values.add(data);
    }
    public void insertData(List<PieData> data){
        for(int i = 0; i< data.size(); i++){
            if(data.get(i).getColor() == null)
                data.get(i).setColor(ColorsPalette.colorPallette.get(i%19));
        }
        this.values = data;
    }
}
