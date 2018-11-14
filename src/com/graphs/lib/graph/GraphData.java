package com.graphs.lib.graph;

public class GraphData<T> {
    private String label;
    private T data;

    public GraphData() {
    }

    public GraphData(String label, T data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
