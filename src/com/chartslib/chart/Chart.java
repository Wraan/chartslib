package com.chartslib.chart;

import com.chartslib.element.*;
import processing.core.PApplet;

abstract class Chart extends PApplet {


    private GraphTitle title = new GraphTitle(this, "no title", 24, Text.Align.TOP, Text.Align.CENTER, new Color(0,0,0));

    protected Boolean isLegendEnabled = true;
    protected Boolean isTitleEnabled = true;
    protected Color backgroundColor = new Color(204, 204, 204);
    protected FileExtension fileExtension;
    protected Boolean isSaveEnabled = false;
    protected Boolean isSaveWithoutDrawingEnabled = false;
    protected String fileName = "";



    Chart() {
        this.width = 800;
        this.height = 600;
    }
    Chart(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void show() {
        PApplet.runSketch(new String[] {Chart.class.getName()},this);
    }

    public void settings(){

    }

    public void setup() {
        surface.setResizable(false);
        if(fileExtension == FileExtension.PDF && isSaveEnabled){
            String file = this.fileName + ".pdf";
            beginRecord(PDF, file);
        }

    }

    @Override
    public void draw(){
        createChart();

        if(isTitleEnabled)
            drawTitle();

        noLoop();
        if(isSaveEnabled && fileExtension != FileExtension.PDF) {
            String file = fileName + "."+ fileExtension.getValue();
            save(file);
        }
        else if(isSaveEnabled)
            endRecord();
        if(isSaveWithoutDrawingEnabled)
            exit();

    }
    protected abstract void createChart();

    public void setTitle(String title, float fontsize, Text.Align vAlign, Text.Align hAlign, Color color){
        setTitle(title);
        setTitleFontSize(fontsize);
        setTitleVAlign(vAlign);
        setTitleHAlign(hAlign);
        setTitleColor(color);
    }
    public void setTitle(String title, float fontsize ){
        setTitle(title);
        setTitleFontSize(fontsize);
    }
    public void setTitle(String title, float fontsize, Text.Align vAlign, Text.Align hAlign){
        setTitle(title);
        setTitleFontSize(fontsize);
        setTitleVAlign(vAlign);
        setTitleHAlign(hAlign);
    }
    public void setTitle(String title){
        this.title.setTitle(title);
    }
    public void setTitleColor(Color color){
        this.title.setColor(color);
    }
    public void setTitleFontSize(float fontSize){
        this.title.setFontsize(fontSize);
    }
    public void setTitleVAlign(Text.Align vAlign){
        this.title.setvAlign(vAlign);
    }
    public void setTitleHAlign(Text.Align hAlign){
        this.title.sethAlign(hAlign);
    }

    void drawTitle(){
        title.draw();
    }

    public void enableLegend(){
        isLegendEnabled = true;
    }

    public void disableLegend(){
        isLegendEnabled = false;
    }

    public void enableTitile(){
        isTitleEnabled = true;
    }

    public void disableTitle(){
        isTitleEnabled = false;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void saveChart(String fileName, FileExtension fileExtension){
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.isSaveEnabled = true;
    }

    public void saveChartWithoutDrawing(String fileName, FileExtension fileExtension) throws InterruptedException {

        saveChart(fileName,fileExtension);
        isSaveWithoutDrawingEnabled = true;
        show();
    }
}
