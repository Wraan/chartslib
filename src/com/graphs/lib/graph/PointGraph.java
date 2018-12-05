package com.graphs.lib.graph;

import com.graphs.lib.graph.data.PointData;
import com.graphs.lib.graph.element.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointGraph extends Graph {
    private String title = "no title";

    private int pointSize = 5;
    private Color pointColor = new Color(127,34,52);

    private PointData points = new PointData("label", Arrays.asList(
            new Point(-10, 5),
            new Point(0, -5),
            new Point(-3,7),
            new Point(0,-2),
            new Point(1,2)
    ));
    private List<PointData> graphData = new ArrayList<>(Arrays.asList(points));

    public PointGraph(int width, int height){
        super(width, height);
    }
    public PointGraph(){
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw(){
        checkColors(graphData);
        drawGraph();
        setTitle("no title", 25, Text.Align.TOP, Text.Align.CENTER, new Color(0,0,0));
        drawTitle();
        drawLegend();

        noLoop();
    }

    private void checkColors(List<PointData> graphData) {
        //TODO: Check colors of the graphData if null give them one from default
    }

    private void drawGraph() {
        graphData = new ArrayList<>(Arrays.asList(points));
        PointGraphArea pointGraphArea = new PointGraphArea(this, new Point(0, 0.1*height), new Point(0.8*width, height), graphData);
        pointGraphArea.draw();
    }

    private void drawLegend() {
        LegendArea legend = new LegendArea(this, new Point(0.8*width, 0.1*height));
        legend.draw();
    }

}
