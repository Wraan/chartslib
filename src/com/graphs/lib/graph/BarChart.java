package com.graphs.lib.graph;

import com.graphs.lib.graph.data.BarData;
import com.graphs.lib.graph.element.*;
import com.graphs.lib.graph.exceptions.InvalidWindowSizeException;

import java.util.ArrayList;
import java.util.List;

abstract class BarChart extends Graph
{

    protected List<Double> axisValues = new ArrayList();
    protected List<BarData> data = new ArrayList();
    protected double maxDataValue;
    protected double minDataValue;
    protected double maxAxisValue = 0;
    protected double minAxisValue = 0;
    protected int stepsAbove = 0;
    protected int stepsUnder = 0;
    double verticalBeginningRatio = 0.1;
    double horizontalRatio = 0.95;
    double verticalRatio = 0.75;
    protected int indexOfAxisZero = 0;
    protected double stepAmount = 0;
    Color chartColor = ColorsPalette.Red;
    float labelFontSize,numericLabelFontSize;
    int thickness = 1;


    BarChart(){
        this.width = 900;
        this.height = 600;
    }
    BarChart(int width, int height) throws InvalidWindowSizeException {
        super(width, height);
    }

    public Color getChartColor() {
        return chartColor;
    }
    public void setChartColor(Color chartColor) {
        this.chartColor = chartColor;
    }

    public float getLabelFontSize() {
        return labelFontSize;
    }
    public void setLabelFontSize(float fontSize) {
        this.labelFontSize = fontSize;
    }
    public float getNumericLabelFontSize() {
        return numericLabelFontSize;
    }
    public void setNumericLabelFontSize(float fontSize) {
        this.numericLabelFontSize = fontSize;
    }

    public void insertData(List<BarData> data){
        this.data = data;
    }
    public void insertData(String label, double data){
        BarData barData = new BarData(label, data);
        this.data.add(barData);
    }
    public void insertData(BarData barData){
        this.data.add(barData);
    }

    public void setStepAmount(double step){
        stepAmount = step;
    }

    void drawVerticalChartLine(double horizontalDistanceFromLeftBorder, double beginningOfLine, double endOfLine){
        Line line = new Line(this, new Point(horizontalDistanceFromLeftBorder, beginningOfLine),
                new Point(horizontalDistanceFromLeftBorder, endOfLine));
        line.draw();
    }
    void drawHorizontalChartLine(double verticalPlacement, double beginningOfLine, double endOfLine) {
        Line line = new Line(this, new Point(beginningOfLine, verticalPlacement),
                new Point(endOfLine, verticalPlacement));
        line.draw();
    }

    void findLowestValue() {
        double minValue = data.get(0).getData();
        for (BarData data : data) {
            if(data.getData() < minValue)
                minValue = data.getData();
        }
        this.minDataValue =  minValue;
    }
    void findHighestValue() {
        double maxValue = data.get(0).getData();
        for (BarData data : data) {
            if(data.getData() > maxValue)
                maxValue = data.getData();
        }
        this.maxDataValue =  maxValue;
    }
    private double roundNumber(double number){
        double calculatedNumber = number;
        if(number >= 1){
            if(number % 10 != 0 && number > 100)
                return Math.ceil(number/10.0) * 10.0;
            if(number < 100 && number > 10 && (int)number % 5 != 0)
                return number + (5 - (number % 5));
            else if(number < 10 && number > 1)
                return  Math.ceil(number * 100)/100.0;
            else
                return number;
        }
        else
            return calculatedNumber;


    }
    private double roundToNPlaces(double number, int n)
    {
        return Math.round(number * Math.pow(10,n)) / Math.pow(10,n);
    }

    //For non default step
    void countNumberOfSteps(){
        double counter = 0;
        while(counter < maxDataValue){
            counter += stepAmount;
            stepsAbove++;
        }
        if(minDataValue < 0){
            counter = 0;
            while(counter > minDataValue){
                counter -= stepAmount;
                stepsUnder++;
            }
        }
    }
    private int findNumberOfDecimalNumbers(double number){
        String text = Double.toString(number);
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces ;
        return decimalPlaces;
    }

    void countNumericAxisValues(){
        if(stepsAbove > 0 && stepsUnder == 0){
            maxAxisValue = stepsAbove * stepAmount;
            for(int i = stepsAbove; i >= 0;i--)
                axisValues.add(i * stepAmount);
            indexOfAxisZero = stepsAbove;
        }
        else if(stepsAbove == 0 && stepsUnder > 0){
            minAxisValue = stepsUnder * stepAmount;
            axisValues.add(0.0);
            for(int i = 1; i <= stepsUnder; i++){
                axisValues.add(i * - stepAmount);
            }
            indexOfAxisZero = 0;
        }
        else{
            maxAxisValue = stepsAbove * stepAmount;
            minAxisValue = stepsUnder * stepAmount;
            if(this instanceof VerticalBarChart)
                indexOfAxisZero = stepsAbove;
            else
                indexOfAxisZero = stepsUnder;
            for(int i = stepsAbove; i >= 0;i--)
                axisValues.add(i * stepAmount);

            for(int i = 1; i <= stepsUnder; i++){
                axisValues.add(i * -stepAmount);
            }
        }
    }
    void countDefaultStepAmount(){
        int decimalNumbers;
        if(minDataValue < 0 && maxDataValue < 0){
            minAxisValue = -roundNumber(-minDataValue);
            decimalNumbers = findNumberOfDecimalNumbers(-minAxisValue/10);
            if(decimalNumbers>5)
                decimalNumbers = 5;
            stepAmount = roundToNPlaces(-minAxisValue/10,decimalNumbers);
            stepsUnder = 10;
            maxAxisValue = 0;
        }
        else if (minDataValue > 0 && maxDataValue > 0){
            maxAxisValue = roundNumber(maxDataValue);
            decimalNumbers = findNumberOfDecimalNumbers(maxAxisValue/10);
            if(decimalNumbers>5)
                decimalNumbers = 5;
            stepAmount = roundToNPlaces(maxAxisValue/10,decimalNumbers);
            stepsAbove = 10;
            minAxisValue = 0;
        }
        else{
            stepAmount = roundNumber(maxDataValue - minDataValue)/10;
            stepsAbove = (int)Math.ceil(maxDataValue /stepAmount);
            stepsUnder = (int)Math.ceil(-minDataValue /stepAmount);
            indexOfAxisZero = stepsAbove;
            maxAxisValue = stepsAbove * stepAmount;
            minAxisValue = stepsUnder * stepAmount;
        }
    }
    public void setChartBarBorderThickness(int thickness){
        this.thickness = thickness;
    }
}
