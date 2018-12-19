package com.graphs.lib.graph.element;

import com.graphs.lib.graph.data.PointData;
import processing.core.PApplet;

import java.util.List;

public class PointGraphArea implements Drawable{
    private PApplet parent;
    private Point start;
    private List<PointData> graphData;

    private double lowestH;
    private double highestH;
    private double lowestV;
    private double highestV;
    private double verticalRatio;
    private double horizonalRatio;
    private float areaWidth;
    private float areaHeight;
    private double xAxis, yAxis;

    private StepType stepType;
    private int horizontalSeparatorsAmount;
    private int verticalSeparatorsAmount;
    private double vertStepDistance;
    private double horStepDistance;
    private int separatorFontSize;
    private int pointSize;

    public PointGraphArea(PApplet parent, Point start, Point end, List<PointData> graphData, PointGraphAreaSettings pointGraphAreaSettings){
        this.parent = parent;
        this.start = start;
        this.graphData = graphData;
        applySettings(pointGraphAreaSettings);
        areaHeight = end.getY() - start.getY();
        areaWidth = end.getX() - start.getX();
    }

    private void applySettings(PointGraphAreaSettings settings) {
        this.stepType = settings.getStepType();
        this.horizontalSeparatorsAmount = settings.getHorizontalSeparatorsAmount();
        this.verticalSeparatorsAmount = settings.getVerticalSeparatorsAmount();
        this.vertStepDistance = settings.getVertStepDistance();
        this.horStepDistance = settings.getHorStepDistance();
        this.separatorFontSize = settings.getSeparatorFontSize();
        this.pointSize = settings.getPointSize();
    }

    @Override
    public void draw() {
        getHighestAndLowestNumbers();
        getVerticalAndHorizontalRatios();
        drawEmptyChart();
        drawSeparatorsWithLabels();
        insertPointsOnChart();
    }
    private void getHighestAndLowestNumbers() {
        lowestV = getLowestY();
        highestV = getHighestY();
        lowestH = getLowestX();
        highestH = getHighestX();
    }
    private double getLowestX() {
        double x = graphData.get(0).getData().get(0).getX();
        for (PointData data : graphData) {
            for (Point point : data.getData()) {
                if (x > point.getX())
                    x = point.getX();
            }
        }
        return x;
    }
    private double getHighestX() {
        double x = graphData.get(0).getData().get(0).getX();
        for (PointData data : graphData) {
            for (Point point : data.getData()) {
                if (x < point.getX())
                    x = point.getX();
            }
        }
        return x;
    }
    private double getLowestY() {
        double y = graphData.get(0).getData().get(0).getY();
        for (PointData data : graphData) {
            for (Point point : data.getData()) {
                if (y > point.getY())
                    y = point.getY();
            }
        }
        return y;
    }
    private double getHighestY() {
        double y = graphData.get(0).getData().get(0).getY();
        for (PointData data : graphData) {
            for (Point point : data.getData()) {
                if (y < point.getY())
                    y = point.getY();
            }
        }
        return y;
    }
    private void getVerticalAndHorizontalRatios() {
        verticalRatio = 0.8 * areaHeight / (highestV - lowestV);
        horizonalRatio = 0.8 * areaWidth / (highestH - lowestH);
    }
    private void drawEmptyChart() {
        drawVerticalGraphLine();
        drawHorizontalGraphLine();
    }
    private void drawHorizontalGraphLine() {
        if(highestV == 0 && lowestV == 0)
            yAxis = start.getY() + 0.5*areaHeight;
        else if(highestV > 0 && lowestV > 0)
            yAxis = start.getY() + 0.9*areaHeight;
        else if(highestV < 0 && lowestV < 0)
            yAxis = start.getY() + 0.1*areaHeight;
        else{
            yAxis = start.getY() + 0.1*areaHeight + (verticalRatio * highestV);
        }
        Line line = new Line(parent, new Point(start.getX() + 0.08*areaWidth, yAxis),
                new Point(start.getX() + 0.92*areaWidth, yAxis));
        line.draw();
    }
    private void drawVerticalGraphLine(){
        if(highestH == 0 && lowestH == 0)
            xAxis = start.getX() + 0.5*areaWidth;
        else if(highestH > 0 && lowestH > 0)
            xAxis = start.getX() + 0.1*areaWidth;
        else if(highestH < 0 && lowestH < 0)
            xAxis = start.getX() + 0.9*areaWidth;
        else {
            xAxis = start.getX() + 0.1*areaWidth + (horizonalRatio * -lowestH);
        }
        Line line = new Line(parent, new Point(xAxis, start.getY() + 0.08*areaHeight),
                new Point(xAxis, start.getY() + 0.92*areaHeight));
        line.draw();
    }
    private void drawSeparatorsWithLabels() {
        drawVerticalSeparatorsWithLabels();
        drawHorizontalSeparatorsWithLabels();
    }
    private void drawHorizontalSeparatorsWithLabels() {
        Line line;
        Text text;
        if(stepType == StepType.STEP_AMMOUNT) {
            double step = (highestH - lowestH) / horizontalSeparatorsAmount;
            for (int i = 0; i <= horizontalSeparatorsAmount; i++) {
                line = new Line(parent, new Point(start.getX() + 0.1 * areaWidth + (0.8 * areaWidth * i / horizontalSeparatorsAmount), yAxis - 0.005 * areaHeight),
                        new Point(start.getX() + 0.1 * areaWidth + (0.8 * areaWidth * i / horizontalSeparatorsAmount), yAxis + 0.005 * areaHeight));
                line.draw();
                text = new Text(parent, Double.toString(lowestH+i*step),
                        new Point(start.getX() + 0.1 * areaWidth + (0.8 * areaWidth * i / horizontalSeparatorsAmount), yAxis + 0.025 * areaHeight));
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(separatorFontSize);
                text.draw();
            }
        }
        else{
            //left
            for(int i = 1; xAxis - (i * horStepDistance * horizonalRatio) >= start.getX() + 0.08 * areaWidth; i++){
                line = new Line(parent, new Point(xAxis - (i * horStepDistance * horizonalRatio), yAxis - 0.005*areaHeight),
                        new Point(xAxis - (i * horStepDistance * horizonalRatio), yAxis + 0.005*areaHeight));
                line.draw();
                text = new Text(parent, Double.toString(-i*horStepDistance),
                        new Point(xAxis - (i * horStepDistance * horizonalRatio), yAxis + 0.025*areaHeight));
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(separatorFontSize);
                text.draw();
            }
            //right
            for(int i = 0; xAxis + (i * horStepDistance * horizonalRatio) <= start.getX() + 0.92*areaWidth; i++){
                line = new Line(parent, new Point(xAxis + (i * horStepDistance * horizonalRatio), yAxis -0.005*areaHeight),
                        new Point(xAxis + (i * horStepDistance * horizonalRatio), yAxis + 0.005*areaHeight));
                line.draw();
                text = new Text(parent, Double.toString(i*horStepDistance),
                        new Point(xAxis + (i * horStepDistance * horizonalRatio), yAxis + 0.025*areaHeight));
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(separatorFontSize);
                text.draw();
            }
        }
    }

    private void drawVerticalSeparatorsWithLabels() {
        Line line;
        Text text;
        if(stepType == StepType.STEP_AMMOUNT){
            double step = (highestV - lowestV) / horizontalSeparatorsAmount;
            for(int i = 0; i <= verticalSeparatorsAmount; i++){
                line = new Line(parent, new Point(xAxis -0.005*areaWidth, start.getY() + 0.1*areaHeight + (0.8*areaHeight * i / verticalSeparatorsAmount)),
                        new Point(xAxis +0.005*areaWidth, start.getY() + 0.1*areaHeight + (0.8*areaHeight * i / verticalSeparatorsAmount)));
                line.draw();
                text = new Text(parent, Double.toString(highestV-i*step),
                        new Point(xAxis -0.008*areaWidth, start.getY() + 0.1*areaHeight + (0.8*areaHeight * i / verticalSeparatorsAmount))
                );
                text.setHorizontalAlign(Text.Align.RIGHT);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(separatorFontSize);
                text.draw();
            }
        }
        else{
            //up
            for(int i = 1; yAxis + (i * vertStepDistance * verticalRatio) <= start.getY() + 0.9 * areaHeight; i++){
                line = new Line(parent, new Point(xAxis -0.005*areaWidth, yAxis + (i * vertStepDistance * verticalRatio)),
                        new Point(xAxis +0.005*areaWidth, yAxis + (i * vertStepDistance * verticalRatio)));
                line.draw();
                text = new Text(parent, Double.toString(-i*vertStepDistance),
                        new Point(xAxis -0.008*areaWidth, yAxis + (i * vertStepDistance * verticalRatio)- 0.003*areaHeight)
                );
                text.setHorizontalAlign(Text.Align.RIGHT);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(separatorFontSize);
                text.draw();
            }
            //down
            for(int i = 1; yAxis - (i * vertStepDistance * verticalRatio) >= start.getY() + 0.1 * areaHeight; i++) {
                line = new Line(parent, new Point(xAxis - 0.005 * areaWidth, yAxis - (i * vertStepDistance * verticalRatio)),
                        new Point(xAxis + 0.005 * areaWidth, yAxis - (i * vertStepDistance * verticalRatio)));
                line.draw();
                text = new Text(parent, Double.toString(i*vertStepDistance),
                        new Point(xAxis -0.008*areaWidth, yAxis - (i * vertStepDistance * verticalRatio) - 0.003*areaHeight)
                );
                text.setHorizontalAlign(Text.Align.RIGHT);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(separatorFontSize);
                text.draw();
            }
        }
    }
    private void insertPointsOnChart() {
        for(PointData data : graphData)
            data.getData().forEach(p->{
                Point middle = new Point(start.getX() + 0.1*areaWidth + (p.getX()-lowestH)*horizonalRatio,
                        start.getY() + 0.1*areaHeight+(highestV-p.getY())*verticalRatio);
                Circle circle = new Circle(parent, middle, pointSize, data.getColor(), 3, data.getColor());
                parent.pixelDensity(parent.displayDensity());
                circle.draw();
            });
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    public void setHorizontalSeparatorsAmount(int horizontalSeparatorsAmount) {
        this.horizontalSeparatorsAmount = horizontalSeparatorsAmount;
    }

    public void setVerticalSeparatorsAmount(int verticalSeparatorsAmount) {
        this.verticalSeparatorsAmount = verticalSeparatorsAmount;
    }

    public void setVertStepDistance(double vertStepDistance) {
        this.vertStepDistance = vertStepDistance;
    }

    public void setHorStepDistance(double horStepDistance) {
        this.horStepDistance = horStepDistance;
    }

    public void setSeparatorFontSize(int separatorFontSize) {
        this.separatorFontSize = separatorFontSize;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }
}
