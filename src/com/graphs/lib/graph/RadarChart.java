package com.graphs.lib.graph;


import com.graphs.lib.graph.element.Point;
import com.graphs.lib.graph.element.Line;
import com.graphs.lib.graph.element.Rectangle;
import com.graphs.lib.graph.element.Text;


import java.util.ArrayList;

public class RadarChart extends Graph {
    private String[] labels;
    private ArrayList<Double[]> data;
    private ArrayList<String> seriesName;
    private Contour contour;
    private String title;

    public RadarChart(String[] labels) {
        this.labels = labels;
        data = new ArrayList<Double[]>();
        setSeriesName(new ArrayList<String>());
        this.setContour(Contour.Polygon);
        setTitle("");
    }

    //TODO add custom exception
    public void addSeries(Double[] series, String seriesName) throws Exception {
        if(labels.length != series.length) {
            throw new Exception("TODO");
        }
        data.add(series);

    }

    public void addSeries(Double[] series) throws Exception {
        this.addSeries(series, "series"+(data.size()+1));
    }

    private void drawEmptyChart() {
        int x = width/2;
        int y = height/2;
        Point center = new Point(x, y);
        float radius = min(x,y)*0.8f;
        for (float a = 0; a < TWO_PI; a += TWO_PI/labels.length) {
            float sx = x + cos(a) * radius;
            float sy = y + sin(a) * radius;
            Line line = new Line(this, center, new Point((int)sx, (int)sy));
            line.draw();
        }
    }

    private void drawLegend() {
        //TODO
        Rectangle textArea = new Rectangle(this, new Point((int)(width*0.8),(int)(height*0.8)), new Point(width-10, height-10));
        textArea.setThickness(3);
        //Text legendTitle = new Text()
    }

    private void drawSeries() {
        //TODO
    }

    private void drawLabels() {
        //TODO
    }




    @Override
    public void draw() {

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
