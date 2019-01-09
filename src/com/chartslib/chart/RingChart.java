package com.chartslib.chart;


import com.chartslib.data.PieData;
import com.chartslib.data.RingData;
import com.chartslib.element.*;
import com.chartslib.exceptions.InvalidDataException;
import com.chartslib.exceptions.InvalidInsertDataException;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class RingChart extends Chart {

    private List<RingData> values = new ArrayList<>();
    private List<RingData> ratios = new ArrayList<>();
    private int uniqueColorsUsed = 0;

    public RingChart() {
        super();
    }
    public RingChart(int width, int height) throws Exception {
        super(width, height);
    }

    private List<PieData> countRatiosOfSeries(List<PieData> values){
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
    private void createArcs(double radius, List<PieData> ratios){
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
    private void drawLegend(List<PieData> pieData){
        List<LegendItem> legendItems = new ArrayList<>();
        for(PieData pd : pieData){
            legendItems.add(new LegendItem(pd.getLabel(),pd.getColor()));
        }
        LegendArea legendArea = new LegendArea(this,new Point(0.67*width,(0.15*height)),legendItems);
        legendArea.draw();
    }

    public void insertData(String title, List<PieData> data) throws Exception {
        if(values.size() == 5)
            throw new InvalidInsertDataException(5);
        if(values.size() == 0)
            for(int i = 0; i<data.size();i++)
                data.get(i).setColor(ColorsPalette.colorPallette.get(i));
        else if(data.size() != values.get(0).getData().size()){
            throw new InvalidInsertDataException(values.get(0).getData().size(),data.size());
        }
        else{
            for(int i = 0; i<data.size(); i++){
                if(data.get(i).getLabel().equals(values.get(0).getData().get(i).getLabel()))
                    data.get(i).setColor(values.get(0).getData().get(i).getColor());
                else
                    throw new InvalidInsertDataException();
            }
        }
        values.add(new RingData(title,data));
    }
    @Override
    protected void createChart() throws InvalidDataException {
        int minimum = min(width,height);
        double radius,step;
        Point center;
        if(values.size() == 0) {
            //insertData("Null", Arrays.asList(new PieData("Null",2.0)));
            throw new InvalidDataException();
        }
        for(int i = 0; i <values.size();i++)
            this.ratios.add(new RingData(values.get(i).getTitle(),countRatiosOfSeries(this.values.get(i).getData())));
        if(isLegendEnabled){
            center = new Point(0.35*width,0.5*height);
            radius = (int)(0.6 * minimum);
            drawLegend(values.get(0).getData());
        }
        else{
            radius = (int)(0.80 * minimum);
            center = new Point(0.5*width,0.5*height);
        }
        step = (radius/2) / ratios.size();
        for(int i = ratios.size(),j=1;i>0;i--,j++)
        {
            createArcs(radius,ratios.get(i-1).getData());
            radius -= step;
        }
        Circle circle = new Circle(this,center, radius/2, getBackgroundColor());
        circle.draw();
        drawTitle();
    }
}
