package com.graphs.lib.graph;


import com.graphs.lib.graph.element.*;


import java.util.ArrayList;

public class RadarChart extends Graph {
    private String[]                    labels;
    private ArrayList<double[]>         data;
    private ArrayList<String>           seriesName;
    private Contour                     contour;
    private String                      title;
    private double                      step;
    private Color                       contourColor;
    private double                      radius;

    public RadarChart(int width, int height) {
        super(width, height);
        this.labels = null;
        data = new ArrayList<double[]>();
        double[] test = new double[3];
        for(int i = 0; i <test.length; i++)
            test[i] = (double)i;
        data.add(test);
        setSeriesName(new ArrayList<String>());
        this.setContour(Contour.Circle);
        setTitle("");
        step = 0.0;
    }

    //TODO add custom exception
    public void addSeries(double[] series, String seriesName) throws Exception {
        if(labels.length != series.length) {
            throw new Exception("TODO");
        }
        data.add(series);

    }

    public void addSeries(double[] series) throws Exception {
        this.addSeries(series, "series"+(data.size()+1));
    }

    private void drawEmptyChart() {
        int x = width/3;
        int y = height/2;
        Point center = new Point(x, y);
        radius = (double)(min(x,y)*0.8f);
        for (float a = 0; a < TWO_PI; a += TWO_PI/data.get(0).length) {
            double sx = x + cos(a - HALF_PI) * (radius + 25);
            double sy = y + sin(a - HALF_PI) * (radius + 25);
            Line line = new Line(this, center, new Point((int)sx, (int)sy));
            line.setThickness(3);
            line.draw();
        }
    }

    private void drawLegend() {
        //TODO drawLegend
        Rectangle textArea = new Rectangle(this, new Point((int)(width*0.8),(int)(height*0.8)), new Point(width-10, height-10));
        textArea.setThickness(3);
        //Text legendTitle = new Text()
    }

    private void drawSeries() {
        //TODO drawSeries
    }

    private void drawLabels() {
        //TODO drawLabels
    }

    private void drawContour()   {
        //TODO Contour, Exception
        if(step == 0.0) {
            step = calculateStep();
        }

        switch(getContour()) {
            case Circle:    drawCircleContour();            break;
            case Polygon:   drawPolygonContour();           break;
            default:
        }

    }


    private double calculateStep() {
        //TODO calcualteStep
        double maxValue = getMaxValueOfSeries();
        if(maxValue > 9) {
            int i = 1;
            while (true) {
                if (maxValue / i > 10) {
                    i *= 10;
                } else {
                    break;
                }
            }
            return maxValue / i;
        } else
            return 1;
    }


    private void drawCircleContour() {
        int x = width/3;
        int y = height/2;
        double maxValue = getMaxValueOfSeries();
        int ratio = (int)(radius/((maxValue/step)+1));
        for(double i = step; i <= maxValue + step; i+= step) {
            Circle circle = new Circle(this, new Point(x, y), (int)(i * ratio));
            circle.setIsFill(false);
            circle.draw();
        }
    }

    private void drawPolygonContour() {
        int x = width/3;
        int y = height/2;
        double maxValue = getMaxValueOfSeries();
        int ratio = (int)(radius/((maxValue/step)+1));
        for(double i = step; i <= maxValue + step; i+= step) {
            RegularPolygon poly = new RegularPolygon(this, new Point(x, y), (int)(i * ratio), data.get(0).length);
            poly.setIsFill(false);
            poly.draw();
        }
    }


    private void setStroke(Color color)
    {
        stroke(color.getR(), color.getG(), color.getB());
    }

    private double getMaxValueOfSeries() {
        double maxValue = 0.0;
        for(double[] series: data) {
            for(double v : series) {
                maxValue = v > maxValue ? v : maxValue;
            }
        }
        return maxValue;
    }

    @Override
    public void draw() {
        drawEmptyChart();
        drawContour();
    }

    public Contour getContour() {
        return contour;
    }

    public void setContour(Contour contour) {
        this.contour = contour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(ArrayList<String> seriesName) {
        this.seriesName = seriesName;
    }

    public enum Contour{
        Circle, Polygon
    }

}