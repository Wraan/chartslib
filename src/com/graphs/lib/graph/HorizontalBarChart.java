package com.graphs.lib.graph;

import com.graphs.lib.graph.element.Line;
import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.Rectangle;
import com.graphs.lib.graph.element.Text;
import com.graphs.lib.graph.exceptions.InvalidWindowSizeException;

import java.util.ArrayList;
import java.util.List;

public class HorizontalBarChart extends BarChart
{

    double horizontalStepRatio = 0;

    public HorizontalBarChart(){
        super();
        setLabelFontSize(0.015f * width);
        setNumericLabelFontSize(0.015f * width);
    }
    public HorizontalBarChart(int width, int height) throws InvalidWindowSizeException {
        super(width, height);
        setLabelFontSize(0.015f * width);
        setNumericLabelFontSize(0.015f * width);
    }

    @Override
    protected void createChart() {
        horizontalRatio = 0.95;
        if(isTitleEnabled)
            verticalBeginningRatio = 0.1;
        else
            verticalBeginningRatio = 0.05;
        verticalRatio = 1 - verticalBeginningRatio - 0.05;

        drawHorizontalChartLine((verticalBeginningRatio + verticalRatio) * height,0.075 * width ,horizontalRatio * width);

        findLowestValue();
        findHighestValue();
        if(stepAmount == 0)
            countDefaultStepAmount();
        else
            countNumberOfSteps();

        countNumericAxisValues();
        reverseOrderOfAxisValues();
        horizontalStepRatio = (horizontalRatio - 0.075 - 0.01) / (stepsAbove + stepsUnder);
        horizontalAxisValuesDraw();
        horizontalAxisDelimitersDraw(stepsAbove + stepsUnder);
        createColumns(0.15);
        verticalAxisValuesDraw();
        verticalAxisDelimitersDraw();
        drawTitle();
    }

    private void horizontalAxisDelimitersDraw(int numberOfLines){
        double ratio = horizontalStepRatio * width;
        for(int i = 0; i <= numberOfLines; i++)
            drawVerticalChartLine(0.08 * width + (ratio * i),verticalBeginningRatio * height,(verticalBeginningRatio + verticalRatio + 0.005) * height);
    }
    private void horizontalAxisValuesDraw(){
        Rectangle rec;
        Text text;
        Point pointStart, pointEnd;
        for(int i =0; i<= stepsAbove+stepsUnder;i++){
            pointStart = new Point((0.08 * width - 0.5 * horizontalStepRatio * width) + i * horizontalStepRatio * width,(verticalBeginningRatio + verticalRatio + 0.005 + 0.005)* height);
            pointEnd = new Point((0.08 * width + 0.5 * horizontalStepRatio * width) + i * horizontalStepRatio * width,0.99 * height);
            rec = new Rectangle(this, pointStart , pointEnd);
            text = new Text(this,Double.toString(axisValues.get(i)), rec, numericLabelFontSize);
            text.setHorizontalAlign(Text.Align.CENTER);
            text.setVerticalAlign(Text.Align.TOP);
            text.draw();
        }
    }
    private void reverseOrderOfAxisValues(){
        List<Double> newList = new ArrayList<>();
        for(int i = axisValues.size()-1; i>=0; i--){
            newList.add(axisValues.get(i));
            if(axisValues.get(i) == 0)
                indexOfAxisZero = newList.size()-1;
        }
        axisValues = newList;
    }
    private void verticalAxisDelimitersDraw(){
        Point pointStart, pointEnd;
        Line line;
        double horizontalPlacement =  0.08 * width + horizontalStepRatio * width * indexOfAxisZero;
        double interval = (verticalRatio * height) / data.size();

        for(int i = 1; i < data.size();i++){
            pointStart = new Point(horizontalPlacement - 0.005 * width, (verticalBeginningRatio) * height + i * interval);
            pointEnd = new Point(horizontalPlacement + 0.005 * width, (verticalBeginningRatio ) * height + i * interval);
            line = new Line(this,pointStart,pointEnd);
            line.draw();
        }
    }
    private void verticalAxisValuesDraw(){
        Text text;
        Rectangle rec;
        Point pointStart, pointEnd;

        double horizontalPlacement =  0.08 * width + horizontalStepRatio * width * indexOfAxisZero;
        double interval = (verticalRatio  * height) / data.size();

        for(int i = 0; i < data.size(); i++){
            pointStart = new Point(horizontalPlacement - 0.005 * width, verticalBeginningRatio * height + i * interval);
            pointEnd = new Point(horizontalPlacement - 3 * horizontalStepRatio * width,verticalBeginningRatio * height + (i+1) * interval);
            rec = new Rectangle(this, pointStart, pointEnd);
            text = new Text(this, data.get(i).getLabel(),rec,0.015f * width);
            text.setHorizontalAlign(Text.Align.RIGHT);
            text.setVerticalAlign(Text.Align.CENTER);
            text.draw();
        }
    }
    private void createColumns(double ratioOfColumnSpaces){
        Point pointStart, pointEnd;
        Rectangle rec;
        double ratioOfSpace,ratio;

        double horizontalStartingPosition = 0.08 * width + horizontalStepRatio * width * indexOfAxisZero;
        double interval = (verticalRatio  * height) / data.size();

        for(int i = 0; i < data.size(); i++){
            pointStart = new Point(horizontalStartingPosition, verticalBeginningRatio * height + ratioOfColumnSpaces * interval + i * interval);

            if(data.get(i).getData() >= 0){
                ratio = data.get(i).getData()/maxAxisValue;
                ratioOfSpace = stepsAbove / (double)(stepsUnder + stepsAbove);
                pointEnd = new Point(horizontalStartingPosition + ratio * ratioOfSpace * (horizontalRatio - 0.075 - 0.01) * width ,verticalBeginningRatio * height + (1-ratioOfColumnSpaces) * interval + i * interval);
            }
            else{
                ratio = data.get(i).getData()/minAxisValue;
                ratioOfSpace = stepsUnder / (double)(stepsUnder + stepsAbove);
                pointEnd = new Point(horizontalStartingPosition + ratio * ratioOfSpace * (horizontalRatio - 0.075 - 0.01) * width ,verticalBeginningRatio * height + (1-ratioOfColumnSpaces) * interval + i * interval);
            }

            rec = new Rectangle(this, pointStart, pointEnd, chartColor,thickness);
            rec.draw();
        }
    }
}