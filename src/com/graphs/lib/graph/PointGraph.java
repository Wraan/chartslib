package com.graphs.lib.graph;

import com.graphs.lib.graph.data.PointData;
import com.graphs.lib.graph.element.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointGraph extends Graph {

    private PointData points = new PointData("label", Arrays.asList(
            new Point(-10, 5),
            new Point(0, -5),
            new Point(-3,7),
            new Point(0,-2),
            new Point(1,2)
    ));
    private PointData points2 = new PointData("label2", Arrays.asList(
            new Point(-3, 2),
            new Point(2, 4)
    ), new Color(32,142,12));

    private List<PointData> graphData = new ArrayList<>(Arrays.asList(points, points2));

    private PointGraphAreaSettings pointGraphAreaSettings = new PointGraphAreaSettings();

    public PointGraph(int width, int height){
        super(width, height);
    }
    public PointGraph(){
        this.width = 900;
        this.height = 600;
    }

    @Override
    public void draw(){
        Point graphStart;
        Point graphEnd;
        if(isTitleEnabled) graphStart = new Point(0,0.1*height);
        else graphStart = new Point(0,0);
        if(isLegendEnabled) graphEnd = new Point(0.8*width,height);
        else graphEnd = new Point(width, height);

        checkColors(graphData);
        drawGraph(graphStart, graphEnd, graphData);

        if(isTitleEnabled)
            drawTitle();
        if(isLegendEnabled)
            drawLegend();

        noLoop();
    }

    private void checkColors(List<PointData> graphData) {
        for(PointData pointData: graphData){
            if(pointData.getColor() == null){
                pointData.setColor(ColorsPalette.colorPallette.get(graphData.indexOf(pointData)));
            }
        }
    }

    private void drawGraph(Point start, Point end, List<PointData> graphData) {
        PointGraphArea pointGraphArea = new PointGraphArea(this, start, end, graphData, pointGraphAreaSettings);
        pointGraphArea.draw();
    }

    private void drawLegend() {
        List<LegendItem> legendItems = getLegendItems(graphData);
        LegendArea legend = new LegendArea(this, new Point(0.8*width, 0.1*height), legendItems);
        legend.draw();
    }

    private List<LegendItem> getLegendItems(List<PointData> graphData) {
        List<LegendItem> legendItems = new ArrayList<>();
        for(PointData pointData : graphData){
            legendItems.add(new LegendItem(pointData.getLabel(), pointData.getColor()));
        }
        return legendItems;
    }

    public void setStepType(StepType stepType) {
        pointGraphAreaSettings.setStepType(stepType);
    }
    public void setHorizontalSeparatorsAmount(int horizontalSeparatorsAmount) {
        pointGraphAreaSettings.setHorizontalSeparatorsAmount(horizontalSeparatorsAmount);
    }
    public void setVerticalSeparatorsAmount(int verticalSeparatorsAmount) {
        pointGraphAreaSettings.setVerticalSeparatorsAmount(verticalSeparatorsAmount);
    }
    public void setVerticalStepDistance(double vertStepDistance) {
        pointGraphAreaSettings.setVertStepDistance(vertStepDistance);
    }
    public void setHorizontalStepDistance(double horStepDistance) {
        pointGraphAreaSettings.setHorStepDistance(horStepDistance);
    }
    public void setSeparatorFontSize(int separatorFontSize) {
        pointGraphAreaSettings.setSeparatorFontSize(separatorFontSize);
    }
    public void setPointSize(int pointSize) {
        pointGraphAreaSettings.setPointSize(pointSize);
    }
    public void addGraphData(String label, List<Point> points){
        graphData.add(new PointData(label, points));
    }
    public void addGraphData(String label, List<Point> points, Color color){
        graphData.add(new PointData(label, points, color));
    }
    public void addGraphData(PointData pointData){
        graphData.add(pointData);
    }

}
