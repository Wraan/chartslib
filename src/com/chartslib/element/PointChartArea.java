package com.chartslib.element;

import com.chartslib.data.PointData;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class  PointChartArea implements Drawable{
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
    private String verticalLabel;
    private String horizontalLabel;
    private int separatorFontSize;
    private int pointSize;
    private Type type = Type.PointChart;

    private int linesThickness;


    public PointChartArea(PApplet parent, Point start, Point end, List<PointData> graphData, PointChartAreaSettings pointChartAreaSettings){
        this.parent = parent;
        this.start = start;
        this.graphData = graphData;
        applySettings(pointChartAreaSettings);
        areaHeight = end.getY() - start.getY();
        areaWidth = end.getX() - start.getX();
    }

    private void applySettings(PointChartAreaSettings settings) {
        this.stepType = settings.getStepType();
        this.horizontalSeparatorsAmount = settings.getHorizontalSeparatorsAmount();
        this.verticalSeparatorsAmount = settings.getVerticalSeparatorsAmount();
        this.vertStepDistance = settings.getVertStepDistance();
        this.horStepDistance = settings.getHorStepDistance();
        this.separatorFontSize = settings.getSeparatorFontSize();
        this.pointSize = settings.getPointSize();
        this.linesThickness = settings.getLinesThickness();
        this.verticalLabel = settings.getVerticalLabel();
        this.horizontalLabel = settings.getHorizontalLabel();
    }

    @Override
    public void draw() {
        getHighestAndLowestNumbers();
        getVerticalAndHorizontalRatios();
        getAxisCoordinates();

        if(type == Type.PointChart)
            drawPointChart();
        else if(type == Type.LineChart)
            drawLineChart();
        else if(type == Type.LayerChart)
            drawLayerChart();

        drawEmptyChart();
        drawSeparatorsWithLabels();
    }

    private void drawPointChart() {
        insertPointsOnChart();
    }
    private void drawLineChart() {
        drawPointChart();
        drawLinesBetweenPoints();
    }
    private void drawLayerChart() {
        drawLayers();
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
    private void getAxisCoordinates(){
        if(highestV == 0 && lowestV == 0)
            yAxis = start.getY() + 0.5*areaHeight;
        else if(highestV > 0 && lowestV > 0)
            yAxis = start.getY() + 0.9*areaHeight;
        else if(highestV < 0 && lowestV < 0)
            yAxis = start.getY() + 0.1*areaHeight;
        else{
            yAxis = start.getY() + 0.1*areaHeight + (verticalRatio * highestV);
        }

        if(highestH == 0 && lowestH == 0)
            xAxis = start.getX() + 0.5*areaWidth;
        else if(highestH > 0 && lowestH > 0)
            xAxis = start.getX() + 0.1*areaWidth;
        else if(highestH < 0 && lowestH < 0)
            xAxis = start.getX() + 0.9*areaWidth;
        else {
            xAxis = start.getX() + 0.1*areaWidth + (horizonalRatio * -lowestH);
        }
    }
    private void drawHorizontalGraphLine() {
        Line line = new Line(parent, new Point(start.getX() + 0.08*areaWidth, yAxis),
                new Point(start.getX() + 0.92*areaWidth, yAxis));
        line.draw();
        Text text = new Text(parent, horizontalLabel, new Rectangle(parent, new Point(start.getX() + 0.92*areaWidth, yAxis ),
                new Point(start.getX() + areaWidth, start.getY() + areaHeight)));
        text.setVerticalAlign(Text.Align.TOP);
        text.setHorizontalAlign(Text.Align.LEFT);
        text.setFontSize(separatorFontSize + 3);
        text.draw();
    }
    private void drawVerticalGraphLine(){
        Line line = new Line(parent, new Point(xAxis, start.getY() + 0.08*areaHeight),
                new Point(xAxis, start.getY() + 0.92*areaHeight));
        line.draw();
        Text text = new Text(parent, verticalLabel, new Rectangle(parent, new Point(xAxis, start.getY()),
                new Point(start.getX() + areaWidth, start.getY() + 0.08*areaHeight)));
        text.setVerticalAlign(Text.Align.BOTTOM);
        text.setHorizontalAlign(Text.Align.LEFT);
        text.setFontSize(separatorFontSize + 3);
        text.draw();
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
            for(int i = 1; yAxis + (i * vertStepDistance * verticalRatio) <= start.getY() + 0.92 * areaHeight; i++){
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
            for(int i = 1; yAxis - (i * vertStepDistance * verticalRatio) >= start.getY() + 0.08 * areaHeight; i++) {
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

    private void drawLinesBetweenPoints() {
        for (PointData pointData : graphData){
            if(pointData.getData().size() < 2) continue;
            for(int i = 0; i < pointData.getData().size()-1; i++){
                Point startPoint = pointData.getData().get(i);
                Point lineStart = new Point(start.getX() + 0.1*areaWidth + (startPoint.getX()-lowestH)*horizonalRatio,
                        start.getY() + 0.1*areaHeight+(highestV-startPoint.getY())*verticalRatio);
                Point endPoint = pointData.getData().get(i+1);
                Point lineEnd = new Point(start.getX() + 0.1*areaWidth + (endPoint.getX()-lowestH)*horizonalRatio,
                        start.getY() + 0.1*areaHeight+(highestV-endPoint.getY())*verticalRatio);
                Line line = new Line(parent, lineStart,lineEnd, linesThickness, pointData.getColor());
                line.draw();
            }
        }
    }

    private void drawLayers(){
        for(PointData pointData : graphData){
            List<Point> sort = new ArrayList<>(pointData.getData());
            sort.sort((o1,o2) -> {
                if(o1.getX() - o2.getX() > 0) return 1;
                else if (o1.getX() - o2.getX() < 0) return -1;
                return 0;
            });
            List<Point> points = new ArrayList<>();
            for(int i = 0; i < sort.size(); i++){
                Point point = new Point(start.getX() + 0.1*areaWidth + (sort.get(i).getX()-lowestH)*horizonalRatio,
                        start.getY() + 0.1*areaHeight+(highestV-sort.get(i).getY())*verticalRatio);
                points.add(point);
            }
            points.add(new Point(start.getX() + 0.1*areaWidth + (sort.get(sort.size()-1).getX()-lowestH)*horizonalRatio,
                    yAxis));
            points.add(new Point(start.getX() + 0.1*areaWidth + (sort.get(0).getX()-lowestH)*horizonalRatio,
                    yAxis));
            points.add(points.get(0));
            Point[] pointsArray = points.toArray(new Point[0]);
            Polygon polygon = new Polygon(parent, pointsArray);
            polygon.setColor(pointData.getColor());
            polygon.setOutColor(pointData.getColor());
            polygon.draw();
        }
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

    public enum Type{
        PointChart, LineChart, LayerChart
    }
    public void setType(Type type) {
        this.type = type;
    }
}
