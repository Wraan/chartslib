package com.graphs.lib.graph;

import com.graphs.lib.graph.data.PointData;
import com.graphs.lib.graph.element.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointGraph extends Graph {
    private StepType stepType = StepType.STEP_DISTANCE;
    private int horizontalSeparatorsAmount = 20;
    private int verticalSeparatorsAmount = 20;
    private double vertStepDistance = 1;
    private double horStepDistance = 1;

    private double lowestH;
    private double highestH;
    private double lowestV;
    private double highestV;

    private double verticalRatio;
    private double horizonalRatio;

    private double xAxis, yAxis;


    private String title = "no title";

    private int pointSize = 5;
    private int fontSize = 12;
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
        getHighestAndLowestNumbers();
        getVerticalAndHorizontalRatios();
        drawEmptyChart();
        drawSeparatorsWithLabels();
        drawTitle();
        insertPointsOnChart();
        drawLegend();

        noLoop();
    }

    /*private void drawTitle() {
        Text title = new Text(this, this.title, new Point(0,0), new Point(width, 0.19*height));
        title.setVerticalAlign(Text.Align.TOP);
        title.setHorizontalAlign(Text.Align.CENTER);
        title.setFontSize(40);
        title.draw();
    }*/

    private void insertPointsOnChart() {
        for(PointData data : graphData)
            data.getData().forEach(p->{
            Point middle = new Point(0.1*width + (p.getX()-lowestH)*horizonalRatio, 0.2*height+(highestV-p.getY())*verticalRatio);
            //TODO: color and size depending on graphData settings
            Circle circle = new Circle(this, middle, pointSize, pointColor, 3, pointColor);
            pixelDensity(displayDensity());
            circle.draw();
        });
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
                line = new Line(this, new Point(0.1 * width + (0.7 * width * i / horizontalSeparatorsAmount), yAxis - 0.005 * height),
                        new Point(0.1 * width + (0.7 * width * i / horizontalSeparatorsAmount), yAxis + 0.005 * height));
                line.draw();
                text = new Text(this, Double.toString(lowestH+i*step),
                        new Point(0.1 * width + (0.7 * width * i / horizontalSeparatorsAmount), yAxis + 0.025 * height));
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(fontSize);
                text.draw();
            }
        }
        else{
            //left
            for(int i = 1; xAxis - (i * horStepDistance * horizonalRatio) >= 0.1 * width; i++){
                line = new Line(this, new Point(xAxis - (i * horStepDistance * horizonalRatio), yAxis -0.005*height),
                        new Point(xAxis - (i * horStepDistance * horizonalRatio), yAxis +0.005*height));
                line.draw();
                text = new Text(this, Double.toString(-i*horStepDistance),
                        new Point(xAxis - (i * horStepDistance * horizonalRatio), yAxis + 0.025 * height));
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(fontSize);
                text.draw();
            }
            //right
            for(int i = 0; xAxis + (i * horStepDistance * horizonalRatio) <= 0.8 * width; i++){
                line = new Line(this, new Point(xAxis + (i * horStepDistance * horizonalRatio), yAxis -0.005*height),
                        new Point(xAxis + (i * horStepDistance * horizonalRatio), yAxis +0.005*height));
                line.draw();
                text = new Text(this, Double.toString(i*horStepDistance),
                        new Point(xAxis + (i * horStepDistance * horizonalRatio), yAxis + 0.025 * height));
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(fontSize);
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
                line = new Line(this, new Point(xAxis -0.005*width, 0.2*height + (0.7*height * i / verticalSeparatorsAmount)),
                        new Point(xAxis +0.005*width, 0.2*height + (0.7*height * i / verticalSeparatorsAmount)));
                line.draw();
                text = new Text(this, Double.toString(highestV-i*step),
                        new Point(xAxis -0.008*width, 0.195*height + (0.7*height * i / verticalSeparatorsAmount))
                        );
                text.setHorizontalAlign(Text.Align.RIGHT);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(fontSize);
                text.draw();
            }
        }
        else{
            //up
            for(int i = 1; yAxis + (i * vertStepDistance * verticalRatio) <= 0.9 * height; i++){
                line = new Line(this, new Point(xAxis -0.005*width, yAxis + (i * vertStepDistance * verticalRatio)),
                        new Point(xAxis +0.005*width, yAxis + (i * vertStepDistance * verticalRatio)));
                line.draw();
                text = new Text(this, Double.toString(-i*vertStepDistance),
                        new Point(xAxis -0.008*width, yAxis + (i * vertStepDistance * verticalRatio)- 0.003*height)
                );
                text.setHorizontalAlign(Text.Align.RIGHT);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(fontSize);
                text.draw();
            }
            //down
            for(int i = 1; yAxis - (i * vertStepDistance * verticalRatio) >= 0.2 * height; i++) {
                line = new Line(this, new Point(xAxis - 0.005 * width, yAxis - (i * vertStepDistance * verticalRatio)),
                        new Point(xAxis + 0.005 * width, yAxis - (i * vertStepDistance * verticalRatio)));
                line.draw();
                text = new Text(this, Double.toString(i*vertStepDistance),
                        new Point(xAxis -0.008*width, yAxis - (i * vertStepDistance * verticalRatio) - 0.003*height)
                );
                text.setHorizontalAlign(Text.Align.RIGHT);
                text.setVerticalAlign(Text.Align.CENTER);
                text.setFontSize(fontSize);
                text.draw();
            }
        }
    }

    private void getVerticalAndHorizontalRatios() {
        verticalRatio = 0.7 * height / (highestV - lowestV);
        horizonalRatio = 0.7 * width / (highestH - lowestH);
    }

    private void getHighestAndLowestNumbers() {
        lowestV = getLowestY();
        highestV = getHighestY();
        lowestH = getLowestX();
        highestH = getHighestX();
    }

    private void drawEmptyChart() {
        drawVerticalGraphLine();
        drawHorizontalGraphLine();
    }

    private void drawLegend() {
        LegendArea legend = new LegendArea(this, new Point(0.8*width, 0.1*height));
        legend.draw();
    }

    private void drawHorizontalGraphLine() {
        if(highestV == 0 && lowestV == 0)
            yAxis = 0.55*height;
        else if(highestV > 0 && lowestV > 0)
            yAxis = 0.9*height;
        else if(highestV < 0 && lowestV < 0)
            yAxis = 0.2*height;
        else{
            yAxis = 0.2*height + (verticalRatio * highestV);
        }
        Line line = new Line(this, new Point(0.08*width, yAxis),
                new Point(0.82*width, yAxis));
        line.draw();
    }
    private void drawVerticalGraphLine(){
        if(highestH == 0 && lowestH == 0)
            xAxis = 0.45*width;
        else if(highestH > 0 && lowestH > 0)
            xAxis = 0.1*width;
        else if(highestH < 0 && lowestH < 0)
            xAxis = 0.8*width;
        else {
            xAxis = 0.1 * width + (horizonalRatio * -lowestH);
        }
        Line line = new Line(this, new Point(xAxis, 0.18*height),
                new Point(xAxis, 0.92*height));
        line.draw();
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

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    public void setHorizontalSeparatorsAmount(int horizontalSeparatorsAmount) {
        this.horizontalSeparatorsAmount = horizontalSeparatorsAmount;
    }

    public void setVerticalSeparatorsAmount(int verticalSeparatorsAmount) {
        this.verticalSeparatorsAmount = verticalSeparatorsAmount;
    }

    public void setVerticalStepDistance(double vertStepDistance) {
        this.vertStepDistance = vertStepDistance;
    }
    public void setHorizontalStepDistance(double horStepDistance) {
        this.horStepDistance = horStepDistance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
