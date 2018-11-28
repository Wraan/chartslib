package com.graphs.lib.graph.data;

import java.util.List;

public class RingData {
    List<PieData> data;
    String title;

    public RingData() {
    }

    public RingData(String title, List<PieData> pieDataList) {
        this.data = pieDataList;
        this.title = title;
    }

    public List<PieData> getData() {
        return data;
    }

    public void setData(List<PieData> data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
