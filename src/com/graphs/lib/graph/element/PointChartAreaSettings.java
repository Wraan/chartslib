package com.graphs.lib.graph.element;

import com.graphs.lib.graph.PointChart;

public class PointChartAreaSettings {
    private StepType stepType = StepType.STEP_DISTANCE;
    private int horizontalSeparatorsAmount = 20;
    private int verticalSeparatorsAmount = 20;
    private double vertStepDistance = 1;
    private double horStepDistance = 1;
    private int separatorFontSize = 12;
    private int pointSize = 8;
    private int linesThickness = 3;

    public StepType getStepType() {
        return stepType;
    }

    public void setStepType(StepType stepType) {
        this.stepType = stepType;
    }

    public int getHorizontalSeparatorsAmount() {
        return horizontalSeparatorsAmount;
    }

    public void setHorizontalSeparatorsAmount(int horizontalSeparatorsAmount) {
        this.horizontalSeparatorsAmount = horizontalSeparatorsAmount;
    }

    public int getVerticalSeparatorsAmount() {
        return verticalSeparatorsAmount;
    }

    public void setVerticalSeparatorsAmount(int verticalSeparatorsAmount) {
        this.verticalSeparatorsAmount = verticalSeparatorsAmount;
    }

    public double getVertStepDistance() {
        return vertStepDistance;
    }

    public void setVertStepDistance(double vertStepDistance) {
        this.vertStepDistance = vertStepDistance;
    }

    public double getHorStepDistance() {
        return horStepDistance;
    }

    public void setHorStepDistance(double horStepDistance) {
        this.horStepDistance = horStepDistance;
    }

    public int getSeparatorFontSize() {
        return separatorFontSize;
    }

    public void setSeparatorFontSize(int separatorFontSize) {
        this.separatorFontSize = separatorFontSize;
    }

    public int getPointSize() {
        return pointSize;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }

    public int getLinesThickness() {
        return linesThickness;
    }

    public void setLinesThickness(int linesThickness) {
        this.linesThickness = linesThickness;
    }
}
