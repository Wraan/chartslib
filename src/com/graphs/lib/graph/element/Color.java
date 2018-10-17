package com.graphs.lib.graph.element;

public class Color {
    private int R;
    private int G;
    private int B;

    public Color(int R, int G, int B){
        this.R = R > 255 ? 255 : R;
        this.G = G > 255 ? 255 : G;
        this.B = B > 255 ? 255 : B;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }
}
