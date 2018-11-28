package com.graphs.lib.graph.element;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LegendArea implements Drawable {
    private PApplet parent;
    private float legendWidth;
    private float legendHeight;

    private Point startPoint;
    private Point endPoint;

    private int fontSize = 12;
    private int colorSignSize = 20;

    private List<LegendItem> items;

    public LegendArea(PApplet parent, Point start){
        this.startPoint = start;
        this.endPoint = new Point(parent.width, parent.height);
        this.parent = parent;
        legendWidth = endPoint.getX() - startPoint.getX();
        legendHeight = endPoint.getY() - startPoint.getY();
        items = new ArrayList<>(Arrays.asList(
                new LegendItem("label", new Color(5,12,35)),
                new LegendItem("labellabellabellabellabellabellabellabellabellabel label label label label label", new Color(5,12,35)),
                new LegendItem("label", new Color(5,12,35)),
                new LegendItem("label", new Color(5,12,35))

        ));
    }
    public LegendArea(PApplet parent, Point start, List<LegendItem> items){
        this.startPoint = start;
        this.endPoint = new Point(parent.width, parent.height);
        this.parent = parent;
        this.items = items;
        legendWidth = endPoint.getX() - startPoint.getX();
        legendHeight = endPoint.getY() - startPoint.getY();
    }


    @Override
    public void draw() {
        if(items.size()== 0) return;

        float step = legendHeight / items.size();
        for(int i = 0; i < items.size(); i++){
            Rectangle rect = new Rectangle(parent,
                    new Point(startPoint.getX() + 0.2*legendWidth - colorSignSize / 2, startPoint.getY() + (i * step) - (colorSignSize / 2)),
                    new Point(startPoint.getX() + 0.2*legendWidth + colorSignSize / 2, startPoint.getY() + (i * step) + (colorSignSize / 2)),
                    items.get(i).getColor());
            rect.draw();
            Text text = new Text(parent, items.get(i).getLabel(), new Rectangle(parent,
                    new Point(startPoint.getX() + 0.2*legendWidth + colorSignSize, startPoint.getY() + (i * step) - fontSize/2),
                    new Point(parent.width, startPoint.getY() + (i * step) + step*0.9)));
            text.setHorizontalAlign(Text.Align.LEFT);
            text.setVerticalAlign(Text.Align.TOP);
            text.setFontSize(fontSize);
            text.draw();
        }
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getColorSignSize() {
        return colorSignSize;
    }

    public void setColorSignSize(int colorSignSize) {
        this.colorSignSize = colorSignSize;
    }

    public List<LegendItem> getItems() {
        return items;
    }

    public void setItems(List<LegendItem> items) {
        this.items = items;
    }
}
