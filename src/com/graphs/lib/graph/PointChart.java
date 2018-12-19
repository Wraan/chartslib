package com.graphs.lib.graph;

import com.graphs.lib.graph.data.PointData;
import com.graphs.lib.graph.element.*;

import java.util.ArrayList;
import java.util.List;

public class PointChart extends Graph {

    protected List<PointData> graphData = new ArrayList<>();

    protected PointChartAreaSettings pointChartAreaSettings = new PointChartAreaSettings();

    public PointChart(int width, int height){
        super(width, height);
    }
    public PointChart(){
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

    protected void checkColors(List<PointData> graphData) {
        for(PointData pointData: graphData){
            if(pointData.getColor() == null){
                pointData.setColor(ColorsPalette.colorPallette.get(graphData.indexOf(pointData)));
            }
        }
    }

    protected void drawGraph(Point start, Point end, List<PointData> graphData) {
        PointChartArea pointChartArea = new PointChartArea(this, start, end, graphData, pointChartAreaSettings);
        pointChartArea.setType(PointChartArea.Type.PointChart);
        pointChartArea.draw();
    }

    protected void drawLegend() {
        List<LegendItem> legendItems = getLegendItems(graphData);
        LegendArea legend = new LegendArea(this, new Point(0.8*width, 0.1*height), legendItems);
        legend.draw();
    }

    protected List<LegendItem> getLegendItems(List<PointData> graphData) {
        List<LegendItem> legendItems = new ArrayList<>();
        for(PointData pointData : graphData){
            legendItems.add(new LegendItem(pointData.getLabel(), pointData.getColor()));
        }
        return legendItems;
    }

    public void setStepType(StepType stepType) {
        pointChartAreaSettings.setStepType(stepType);
    }
    public void setHorizontalSeparatorsAmount(int horizontalSeparatorsAmount) {
        pointChartAreaSettings.setHorizontalSeparatorsAmount(horizontalSeparatorsAmount);
    }
    public void setVerticalSeparatorsAmount(int verticalSeparatorsAmount) {
        pointChartAreaSettings.setVerticalSeparatorsAmount(verticalSeparatorsAmount);
    }
    public void setVerticalStepDistance(double vertStepDistance) {
        pointChartAreaSettings.setVertStepDistance(vertStepDistance);
    }
    public void setHorizontalStepDistance(double horStepDistance) {
        pointChartAreaSettings.setHorStepDistance(horStepDistance);
    }
    public void setSeparatorFontSize(int separatorFontSize) {
        pointChartAreaSettings.setSeparatorFontSize(separatorFontSize);
    }
    public void setPointSize(int pointSize) {
        pointChartAreaSettings.setPointSize(pointSize);
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
