package com.graphs.lib.graph;


import com.graphs.lib.graph.element.*;


import java.util.ArrayList;

public class RadarChart extends Graph {
    private String[]                                labels = null;
    private ArrayList<GraphData<double[]>>          data = null;
    private Contour                                 contour = Contour.Circle;
    private String                                  title = "";
    private double                                  step = 0.0;
    private Color                                   contourColor = new Color(0, 0, 0);
    private float                                   radius;
    private float                                   radiusStep;
    private Point                                   graphCenter = new Point(width/3+10, height/2 + 30);
    public RadarChart(int width, int height) {
        super(width, height);
    }

    //TODO add custom exception of incorrect series length
    public void addSeries(GraphData<double[]> series)  {
        if(data == null) {
            data = new ArrayList<GraphData<double[]>>();
            data.add(series);
        } else {
            if(data.get(0).getData().length != series.getData().length) {
                //TODO: throw new Exception("");
            }
            else {
                data.add(series);
            }
        }
    }

    public void addSeries(double[] series, String label) {
        GraphData<double[]> newSeries = new GraphData<>(label, series);
        this.addSeries(newSeries);
    }


    @Override
    public void draw() {
        drawTitle();
        drawEmptyChart();
        drawContour();
        drawData();
        drawLabels();
        noLoop();
    }


    private void drawEmptyChart() {
        radius = (min(graphCenter.getX(),graphCenter.getY())*0.6f);
        for (float a = 0; a < TWO_PI; a += TWO_PI/data.get(0).getData().length) {
            Point point = polarCoordinatesInGraph(a - HALF_PI, radius + 10);
            Line line = new Line(this, graphCenter, point);
            line.setThickness(3);
            line.draw();
        }
    }

    private void drawTitle() {
        Rectangle textArea = new Rectangle(this, new Point(0, 0), new Point(width, height));
        Text text = new Text(this, title, textArea);
        text.setFontSize(30);
        //TODO add centering
        text.draw();
    }

    private void drawSeries(GraphData<double[]> series) {
        float a = 0.0f;
        Point[] points = new Point[series.getData().length];
        for(int i = 0; i < points.length; i++, a += TWO_PI/series.getData().length) {
            points[i] = polarCoordinatesInGraph(a - HALF_PI, radiusStep * (float)series.getData()[i]);
        }
        Polygon polygon = new Polygon(this, points);
        polygon.setIsFill(false);
        polygon.setOutColor(new Color(255, 0, 255));
        polygon.draw();
    }

    private void drawLabels() {
        //TODO drawLabels function
        for (int i = 0; i < labels.length; i++) {
            Point point = polarCoordinatesInGraph(i * radiusStep, radius);
            point.setX(point.getX() - graphCenter.getX());
            point.setY(point.getY() - graphCenter.getY());
        }
    }
    private void drawContour()   {
        //TODO more Contour, Exception
        if(step == 0.0) {
            step = calculateStep();
        }

        switch(getContour()) {
            case Circle:    drawCircleContour();            break;
            case Polygon:   drawPolygonContour();           break;
            default:
        }

    }

    private void drawData() {
        for(GraphData<double[]> series: data) {
            drawSeries(series);
        }
    }


    private double calculateStep() {
        //TODO calcualteStep function
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
        radiusStep = (float)step * ratio;
        for(double i = step; i <= maxValue + step; i+= step) {
            Circle circle = new Circle(this, new Point(x, y), (int)(i * ratio));
            circle.setOutColor(contourColor);
            circle.setIsFill(false);
            circle.draw();
        }
    }

    private void drawPolygonContour() {
        float x = graphCenter.getX();
        float y = graphCenter.getY();
        double maxValue = getMaxValueOfSeries();
        int ratio = (int)(radius/((maxValue/step)+1));
        radiusStep = (float)step * ratio;
        for(double i = step; i <= maxValue + step; i+= step) {
            RegularPolygon poly = new RegularPolygon(this, new Point(x, y), (int)(i * ratio), data.get(0).getData().length);
            poly.setOutColor(contourColor);
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
        for(GraphData<double[]> series: data) {
            for(double v : series.getData()) {
                maxValue = v > maxValue ? v : maxValue;
            }
        }
        return maxValue;
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

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        //TODO add exception of incorrect labels length
        this.labels = labels;
    }

    private Point polarCoordinatesInGraph(float angle, float radius) {
        double x = graphCenter.getX() + cos(angle) * radius;
        double y = graphCenter.getY() + sin(angle) * radius;
        return new Point(x, y);
    }

    public enum Contour{
        Circle, Polygon
    }

}