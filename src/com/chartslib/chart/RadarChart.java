package com.chartslib.chart;


import com.chartslib.data.RadarData;
import com.chartslib.element.*;
import com.chartslib.exceptions.InvalidValueException;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class RadarChart extends Chart {
    //Fields
    private String[]                                labels              = null;
    private ArrayList<RadarData>                    data                = null;
    private ContourType                             contourType         = ContourType.Circle;
    private double                                  step                = 0.0;
    private Color                                   contourColor        = new Color(101, 101, 101);
    private float                                   radius;
    private Point                                   graphCenter;
    private StepType                                stepType            = StepType.STEP_AMMOUNT;
    private double                                  maxValueOfSeries    = -1;
    private int                                     contourNumber       = 10;
    private double                                  radiusRatio;
    //~Fields
    //--------------------------------------------------------------------------------------------
    //Constants
    private final int                               maxContourNumber    = 10;
    //~Constants
    //--------------------------------------------------------------------------------------------
    //Internal DataType
    public enum ContourType {
        Circle, Polygon
    }
    //~Internal DataType
    //--------------------------------------------------------------------------------------------
    //Getter and Setter

    public void setStepDistance(double step) {
        if(step <= 0)
            throw new InvalidValueException("step must to be greater then 0");
        this.step = step;
    }
    public void setStepType(StepType stepType) {
        this.stepType = stepType;
        if(stepType == StepType.STEP_DISTANCE) {
            step = maxValueOfSeries / maxContourNumber;
        }
    }

    public void setContourType(ContourType contourType) {
        this.contourType = contourType;
    }

    public void setLabels(String[] labels) {
        if(data == null) {
            this.labels = labels;
        }
        else {
            if(data.get(0).getData().length != labels.length) {
                throw new InvalidValueException("Series_length="+data.get(0).getData().length+"; labels_length="+labels.length);
            }
            else {
                this.labels = labels;
            }
        }
    }
    //~Getter and Setter
    //--------------------------------------------------------------------------------------------
    //Constructors
    public RadarChart(int width, int height) {
        super(width, height);
    }

    public RadarChart() {
        this(800, 600);
    }
    //~Constructors
    //--------------------------------------------------------------------------------------------
    //Public methods
    public void addSeries(RadarData series)  {
        if(data == null) {
            data = new ArrayList<RadarData>();
            data.add(series);
        } else {
            if(data.get(0).getData().length != series.getData().length) {
                throw new InvalidValueException("New series_length="+series.getData().length+"; Previous series_length="+data.get(0).getData().length);
            }
            else if(labels != null && series.getData().length != labels.length) {
                throw new InvalidValueException("New series_length="+series.getData().length+"; labels_length="+labels.length);
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
    //~Public methods
    //--------------------------------------------------------------------------------------------
    //Inherited methods
    @Override
    protected void createChart() {
        prepareData();
        calculateProportion();
//        drawTitle();
        drawEmptyChart();
        drawContour();
        drawEmptyChart();
        drawData();
        drawStep();
        drawLabels();
        drawLegends();
        noLoop();
    }
    //~Inherited methods
    //--------------------------------------------------------------------------------------------
    //Private methods
    private void calculateProportion() {
        graphCenter = new Point(1.0f*width/2, 1.0f*height/2);
        if(isLegendEnabled) {
            graphCenter.setX(1.0f*width/3);
        }
        if(isTitleEnabled) {
            graphCenter.setY((height*0.9f)/2 + 0.1f*height);
        }
    }

    private void prepareData() {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getColor() == null) {
                data.get(i).setColor(ColorsPalette.colorPallette.get(i % ColorsPalette.colorPallette.size()));
            }
            if(data.get(i).getLabel() == null) {
                data.get(i).setLabel("Series " + (i + 1));
            }
        }
        maxValueOfSeries = getMaxValueOfSeries();
        for(RadarData rd: data) {
            for(int i = 0; i < rd.getData().length; i++)
            {
                rd.getData()[i] /= maxValueOfSeries;
            }
        }
    }

    private void drawEmptyChart() {
        radius = (min(graphCenter.getX(),graphCenter.getY()))*0.75f;
        for (float a = 0; a < TWO_PI; a += TWO_PI/data.get(0).getData().length) {
            Point point = polarCoordinatesInGraph(a - HALF_PI, radius);
            Line line = new Line(this, graphCenter, point);
            line.setThickness(3);
            line.draw();
        }
    }

    private void drawSeries(RadarData series) {
        Point[] points = new Point[series.getData().length];
        for(int i = 0; i < points.length; i++) {
            float a = TWO_PI/series.getData().length * i;
            points[i] = polarCoordinatesInGraph(a - HALF_PI, (float)(radius * series.getData()[i]));
        }
        Polygon polygon = new Polygon(this, points);
        polygon.setIsFill(false);
        polygon.setOutColor(new Color(255, 0, 255));
        polygon.setThickness(2);
        polygon.setOutColor(series.getColor());
        polygon.draw();
    }

    private void drawLabels() {
        float angle = - HALF_PI;
        for (int i = 0; i < labels.length; i++, angle += TWO_PI / labels.length) {
            Point point = polarCoordinatesInGraph(angle, radius + 5);
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
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
            } else if(newAngel <= PI * 3 / 4) {
                //RIGHT
                //text align left
                //box (x, y-textHeight/2, x + textWidth, y+textHeight/2)
                rect = new Rectangle(this, new Point(point.getX(), point.getY()-textHeight/2), new Point(point.getX() + textWidth, point.getY() + textHeight/2));
                text = new Text(this, labels[i], rect.getLeftUp(), rect.getRightDown());
                text.setHorizontalAlign(Text.Align.LEFT);
                text.setVerticalAlign(Text.Align.CENTER);
            } else if(newAngel <= PI * 5 / 4) {
                //BOTTOM
                //text align center
                //position (x-textWidth/2, y - textHeight/2, x + textWidth/2, y + textHeight/2)
                rect = new Rectangle(this, new Point(point.getX() - textWidth/2, point.getY()), new Point(point.getX() + textWidth/2, point.getY() + textHeight));
                text = new Text(this, labels[i], rect.getLeftUp(), rect.getRightDown());
                text.setHorizontalAlign(Text.Align.CENTER);
                text.setVerticalAlign(Text.Align.CENTER);
            } else if(newAngel <= PI * 7 / 4) {
                //LEFT
                //text align right
                //position (x-textWidth/2, y - textHeight/2, x, y + textHeight/2)
                rect = new Rectangle(this, new Point(point.getX() - textWidth, point.getY()-textHeight/2), new Point(point.getX(),point.getY() + textHeight/2));
                text = new Text(this, labels[i], rect.getLeftUp(), rect.getRightDown());
                text.setHorizontalAlign(Text.Align.RIGHT);
                text.setVerticalAlign(Text.Align.CENTER);
            }
            text.setFontSize(14);
            text.draw();
        }
    }

    private void drawLegends() {
        ArrayList<LegendItem> legendItems = new ArrayList<>();
        for(RadarData d: data) {
            LegendItem item = new LegendItem(d.getLabel(), d.getColor());
            legendItems.add(item);
        }
        LegendArea legend = new LegendArea(this, new Point(2*width/3, 0.15*height), legendItems);
        legend.draw();
    }

    private void drawContour()   {
        calculateStep();

        switch(contourType) {
            case Circle:    drawCircleContour();            break;
            case Polygon:   drawPolygonContour();           break;
            default:
        }
    }

    private void drawStep() {
        for(int i = 1; i < maxValueOfSeries/step + 1; i++) {
            Point p = polarCoordinatesInGraph(-HALF_PI, (float)(i * radiusRatio));
            String t = "";
            Text text;
            if(step >= 0.5)
                text = new Text(this, round(i*step, 2), p);
            else
                text = new Text(this, Double.toString(i*step), p);
            text.setFontSize(14);
            text.draw();
        }
    }

    private void drawData() {
        for(RadarData series: data) {
            drawSeries(series);
        }
    }

    private void calculateStep() {
        if(StepType.STEP_AMMOUNT == stepType) {
            step = maxValueOfSeries / maxContourNumber;
            contourNumber = maxContourNumber;
        } else {
            contourNumber = ceil((float)(maxValueOfSeries / step));
        }

    }

    private void drawCircleContour() {
        radiusRatio = radius/contourNumber;
        for(int i = 1; i < maxValueOfSeries/(step) + 1; i++) {
            Circle circle = new Circle(this, graphCenter, (int)(i * radiusRatio));
            circle.setOutColor(contourColor);
            circle.setIsFill(false);
            circle.draw();
        }
    }

    private void drawPolygonContour() {
        radiusRatio = radius/contourNumber;
        for(int i = 1; i < maxValueOfSeries/(step) + 1; i++) {
            RegularPolygon poly = new RegularPolygon(this, graphCenter, (int) (i* radiusRatio), data.get(0).getData().length);
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

    private Point polarCoordinatesInGraph(float angle, float radius) {
        double x = graphCenter.getX() + cos(angle) * radius;
        double y = graphCenter.getY() + sin(angle) * radius;
        return new Point(x, y);
    }

    private static String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.toString();
    }
    //~Private methods
}