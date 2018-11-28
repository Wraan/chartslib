package com.graphs.lib.graph;


import com.graphs.lib.graph.data.PieData;
import com.graphs.lib.graph.data.RingData;
import com.graphs.lib.graph.element.Circle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RingChart extends PieChart{

    private List<RingData> values = new ArrayList<>();
    private List<RingData> ratios = new ArrayList<>();

    public RingChart() {
    }

    public RingChart(int width, int height) throws Exception {
        super(width, height);
    }

    public void insertData(String title, List<PieData> data) throws Exception {
        if(values.size() == 5)
            throw new Exception("Ring chart can contain only 5 series of data");
        values.add(new RingData(title,data));
    }

    private void createChart(){
        int minimum = min(width,height);
        double radius,radiusFixed,step;
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
            radius = (int)(0.6 * minimum);
            createLegend();
            drawLegend();
        }
        else
            radius = (int)(0.80 * minimum);
        step = (radius/2) / ratios.size();
        radiusFixed = radius;
        for(int i = ratios.size(),j=1;i>0;i--,j++)
        {
            createArcs(radiusFixed,ratios.get(i-1).getData());
            radiusFixed -= step;
        }
        createArcs(radiusFixed, Arrays.asList(new PieData("null",1.0)));
        drawTitle();
    }

    @Override
    public void draw(){
        createChart();
        noLoop();
    }
}
