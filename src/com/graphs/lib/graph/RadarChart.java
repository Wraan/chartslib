package com.graphs.lib.graph;


import com.graphs.lib.graph.data.RadarData;
import com.graphs.lib.graph.element.*;


import java.util.ArrayList;

public class RadarChart extends Graph {
    private String[]                                labels = null;
    private ArrayList<RadarData>                    data = null;
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
    public void addSeries(RadarData series)  {
        if(data == null) {
            data = new ArrayList<RadarData>();
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
        RadarData newSeries = new RadarData(series, label);
        this.addSeries(newSeries);
    }

    @Override
    public void draw() {
        drawTitle();
        drawEmptyChart();
        drawContour();
        drawData();
        drawLabels();
        drawLegends();
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

    private void drawSeries(RadarData series) {
        float a = 0.0f;
        Point[] points = new Point[series.getData().length];
        for(int i = 0; i < points.length; i++, a += TWO_PI/series.getData().length) {
            points[i] = polarCoordinatesInGraph(a - HALF_PI, radiusStep * (float)series.getData()[i]);
        }
        Polygon polygon = new Polygon(this, points);
        polygon.setIsFill(false);
        polygon.setOutColor(new Color(255, 0, 255));
        polygon.setThickness(2);
        polygon.draw();
    }

    private void drawLabels() {
        //TODO add text aligned
        float angle = - HALF_PI;
        for (int i = 0; i < labels.length; i++, angle += TWO_PI / labels.length) {
            Point point = polarCoordinatesInGraph(angle, radius + 15);
            float newAngel = angle + HALF_PI;
            int textWidth = labels[i].length()*14;
            int textHeight = 25;
            Rectangle rect = null;
            Text text = null;

            if(newAngel  <= PI / 4 || newAngel >= PI * 7 / 4) {
                //TOP
                //text align center
                //position (x-textWidth/2, y-textHeight/2, x + textWidth/2, y+textHeight/2)
                rect = new Rectangle(this, new Point(point.getX() - textWidth/2, point.getY() - textHeight), new Point(point.getX() + textWidth/2, point.getY()));
                text = new Text(this, labels[i], rect.getLeftUp(), rect.getRightDown());
                //text.setHorizontalAlign("CENTER");
                //text.setVerticalAlign("CENTER");
            } else if(newAngel <= PI * 3 / 4) {
                //RIGHT
                //text align left
                //box (x, y-textHeight/2, x + textWidth, y+textHeight/2)
                rect = new Rectangle(this, new Point(point.getX(), point.getY()-textHeight/2), new Point(point.getX() + textWidth, point.getY() + textHeight/2));
                text = new Text(this, labels[i], rect.getLeftUp(), rect.getRightDown());
                //text.setHorizontalAlign("LEFT");
                //text.setVerticalAlign("CENTER");
            } else if(newAngel <= PI * 5 / 4) {
                //BOTTOM
                //text align center
                //position (x-textWidth/2, y - textHeight/2, x + textWidth/2, y + textHeight/2)
                rect = new Rectangle(this, new Point(point.getX() - textWidth/2, point.getY()), new Point(point.getX() + textWidth/2, point.getY() + textHeight));
                text = new Text(this, labels[i], rect.getLeftUp(), rect.getRightDown());
               //text.setHorizontalAlign("CENTER");
               //text.setVerticalAlign("CENTER");
            } else if(newAngel <= PI * 7 / 4) {
                //LEFT
                //text align right
                //position (x-textWidth/2, y - textHeight/2, x, y + textHeight/2)
                rect = new Rectangle(this, new Point(point.getX() - textWidth, point.getY()-textHeight/2), new Point(point.getX(),point.getY() + textHeight/2));
                text = new Text(this, labels[i], rect.getLeftUp(), rect.getRightDown());
                //text.setHorizontalAlign("RIGHT");
                //text.setVerticalAlign("CENTER");
            }
            text.setFontSize(14);
            text.draw();
        }
    }

    private void drawLegends() {
        //TODO drawLegends function

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
        for(RadarData series: data) {
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
        for(RadarData series: data) {
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