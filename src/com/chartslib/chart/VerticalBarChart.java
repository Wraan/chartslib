package com.chartslib.chart;

import com.chartslib.element.Line;
import com.chartslib.element.Point;
import com.chartslib.element.Rectangle;
import com.chartslib.element.Text;
import com.chartslib.exceptions.InvalidWindowSizeException;

public class VerticalBarChart extends BarChart {

    double verticalStepRatio = 0;

    public VerticalBarChart(){
        super();
        setLabelFontSize(0.02f * height);
        setNumericLabelFontSize(0.015f * height);
    }
    public VerticalBarChart(int width, int height) throws InvalidWindowSizeException {
        super(width,height);
        setLabelFontSize(0.02f * height);
        setNumericLabelFontSize(0.015f * height);
    }

    @Override
    protected void createChart() {
        horizontalRatio = 0.95;
        if(isTitleEnabled)
            verticalBeginningRatio = 0.1;
        else
            verticalBeginningRatio = 0.05;
        verticalRatio = 1 - verticalBeginningRatio - 0.05;


        drawVerticalChartLine(0.08 * width,verticalBeginningRatio * height,(verticalBeginningRatio + verticalRatio + 0.02)*height);

        findLowestValue();
        findHighestValue();

        if(stepAmount == 0)
            countDefaultStepAmount();
        else
            countNumberOfSteps();

        countNumericAxisValues();
        verticalStepRatio = verticalRatio / (stepsAbove + stepsUnder);
        verticalAxisDelimitersDraw( stepsAbove + stepsUnder);
        verticalAxisValuesDraw();
        createBars(0.15);
        horizontalAxisValuesDraw();
        horizontalAxisDelimitersDraw();
        drawTitle();
    }

    private void verticalAxisDelimitersDraw(int numberOfLines){
        double ratio = verticalStepRatio * height;
        for(int i = 0; i <= numberOfLines; i++)
            drawHorizontalChartLine((verticalBeginningRatio + 0.01) * height + i * ratio,0.075 * width,horizontalRatio * width);
    }
    private void verticalAxisValuesDraw(){
        Rectangle rec;
        Text text;
        Point pointStart, pointEnd;
        for(int i =0; i<= stepsAbove+stepsUnder;i++){
            pointStart = new Point(0.005 * width,(verticalBeginningRatio - 0.01) * height + i * verticalStepRatio * height);
            pointEnd = new Point(0.074 * width,(verticalBeginningRatio + 0.03) * height + i * verticalStepRatio * height);
            rec = new Rectangle(this, pointStart , pointEnd);
            text = new Text(this,Double.toString(axisValues.get(i)), rec, numericLabelFontSize);
            text.setHorizontalAlign(Text.Align.RIGHT);
            text.setVerticalAlign(Text.Align.CENTER);
            text.draw();
        }
    }
    private void horizontalAxisDelimitersDraw(){
        Point pointStart, pointEnd;
        Line line;
        double verticalHeight = (verticalBeginningRatio + 0.01) * height + verticalStepRatio * height * indexOfAxisZero;
        double interval = ((horizontalRatio - 0.08) * width) / data.size();

        for(int i = 1; i < data.size();i++){
            pointStart = new Point(0.08 * width + i * interval, verticalHeight + 0.005 * height);
            pointEnd = new Point(0.08 * width + i * interval, verticalHeight - 0.005 * height);
            line = new Line(this,pointStart,pointEnd);
            line.draw();
        }
    }
    private void horizontalAxisValuesDraw(){
        Text text;
        Rectangle rec;
        Point pointStart, pointEnd;

        double verticalPlacement = (verticalBeginningRatio + 0.01) * height + verticalStepRatio * height * indexOfAxisZero;
        double interval = ((horizontalRatio - 0.08) * width) / data.size();

        for(int i = 0; i < data.size(); i++){
            pointStart = new Point(0.08 * width + i * interval, verticalPlacement);
            pointEnd = new Point(0.08 * width + (i+1) * interval,verticalPlacement +0.05*height);
            rec = new Rectangle(this, pointStart, pointEnd);
            text = new Text(this, data.get(i).getLabel(),rec,labelFontSize);
            text.setHorizontalAlign(Text.Align.CENTER);
            text.draw();
        }
    }
    private void createBars(double ratioOfColumnSpaces){
        Point pointStart, pointEnd;
        Rectangle rec;
        double ratioOfSpace,ratio;

        double verticalStartingHeight = (verticalBeginningRatio + 0.01) * height + verticalStepRatio * height * indexOfAxisZero;
        double interval = ((horizontalRatio - 0.08) * width) / data.size();

        for(int i = 0; i < data.size(); i++){
            pointStart = new Point(0.08 * width + ratioOfColumnSpaces * interval + i * interval, verticalStartingHeight);

            if(data.get(i).getData() >= 0){
                ratio = data.get(i).getData()/maxAxisValue;
                ratioOfSpace = stepsAbove / (double)(stepsUnder + stepsAbove);
                pointEnd = new Point(0.08 * width + (1 - ratioOfColumnSpaces) * interval + i * interval,verticalStartingHeight - ratio * ratioOfSpace * verticalRatio * height);
            }
            else{
                ratio = data.get(i).getData()/minAxisValue;
                ratioOfSpace = stepsUnder / (double)(stepsUnder + stepsAbove);
                pointEnd = new Point(0.08 * width + (1 - ratioOfColumnSpaces) * interval + i * interval,verticalStartingHeight - ratio * ratioOfSpace * verticalRatio * height);
            }

            rec = new Rectangle(this, pointStart, pointEnd, chartColor, thickness);
            rec.draw();
        }
    }
}