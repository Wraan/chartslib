package com.chartslib.chart;

import com.chartslib.element.Color;
import com.chartslib.element.FileExtension;
import com.chartslib.element.GraphTitle;
import com.chartslib.element.Text;
import com.chartslib.exceptions.InvalidWindowSizeException;
import com.chartslib.exceptions.WindowException;
import processing.core.PApplet;

abstract class Chart extends PApplet {

    private Boolean isDrawingStarted = false;
    Boolean isLegendEnabled = true;
    Boolean isTitleEnabled = true;
    private Color backgroundColor = new Color(204, 204, 204);
    private FileExtension fileExtension;
    private Boolean isSaveEnabled = false;
    private Boolean isSaveWithoutDrawingEnabled = false;
    private String fileName = "";
    private GraphTitle title = new GraphTitle(this, "no title", 24, Text.Align.TOP, Text.Align.CENTER, new Color(0,0,0));


    Chart() {
        this.width = 800;
        this.height = 600;
    }
    Chart(int width, int height) throws InvalidWindowSizeException {
        if(width < 800 || height < 600)
            throw new InvalidWindowSizeException();
        this.width = width;
        this.height = height;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
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
    protected abstract void createChart() throws Exception;
    public void show(){
        if(isDrawingStarted)
            throw new WindowException("Only one window can be showed.");
        else{
            isDrawingStarted = true;
            PApplet.runSketch(new String[] {Chart.class.getName()},this);
        }
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
    public void draw(){
        background(backgroundColor.getR(),backgroundColor.getG(),backgroundColor.getB());
        noLoop();
        try {
            createChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(isTitleEnabled)
            drawTitle();

        if(isSaveEnabled && fileExtension != FileExtension.PDF) {
            String file = fileName + "."+ fileExtension.getValue();
            save(file);
        }
        else if(isSaveEnabled)
            endRecord();
        if(isSaveWithoutDrawingEnabled)
            exit();

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
    public void enableSaving(String fileName, FileExtension fileExtension){
        if(isDrawingStarted)
            throw new WindowException("Cannot save after show method. Call this method before method show().");
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.isSaveEnabled = true;
    }
    public void enableSavingWithoutDrawing(String fileName, FileExtension fileExtension) {
        enableSaving(fileName,fileExtension);
        isSaveWithoutDrawingEnabled = true;
        show();
    }
}
