package com.graphs.lib.graph;


import com.graphs.lib.graph.data.PieData;
import com.graphs.lib.graph.data.RingData;
import com.graphs.lib.graph.element.Circle;
import com.graphs.lib.graph.element.Color;
import com.graphs.lib.graph.element.ColorsPalette;
import com.graphs.lib.graph.element.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RingChart extends PieChart{

    private List<RingData> values = new ArrayList<>();
    private List<RingData> ratios = new ArrayList<>();
    private int uniqueColorsUsed = 0;

    public RingChart() {
    }

    public RingChart(int width, int height) throws Exception {
        super(width, height);
    }

    public void insertData(String title, List<PieData> data) throws Exception {
        if(values.size() == 5)
            //Todo: Exception that says that ring chart can contain maximum 5 rings
            throw new Exception("Ring chart can contain only 5 series of data");
        if(values.size() == 0)
            for(int i = 0; i<data.size();i++)
                data.get(i).setColor(ColorsPalette.colorPallette.get(i));
        else if(data.size() != values.get(0).getData().size()){
            //Todo: Exception that says that number of labels must match the first ring
            throw new Exception("Number of labels must be the same in all rings");
        }
        else{
            for(int i = 0; i<data.size(); i++){
                if(data.get(i).getLabel().equals(values.get(0).getData().get(i).getLabel()))
                    data.get(i).setColor(values.get(0).getData().get(i).getColor());
                else
                    //Todo: Exception that says that series must have same names and placed in same order
                    throw new Exception("Labels must be the same");
            }
        }
        values.add(new RingData(title,data));
    }
    private void createChart(){
        int minimum = min(width,height);
        double radius,step;
        Point center;
        if(values.size() == 0) {
            try {
                insertData("Null", Arrays.asList(new PieData("Null",2.0)));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        Circle circle = new Circle(this,center, radius/2, ColorsPalette.BackroundColor);
        circle.draw();
        drawTitle();
    }

    @Override
    public void draw(){
        createChart();
        noLoop();
        endRecord();
    }
}
