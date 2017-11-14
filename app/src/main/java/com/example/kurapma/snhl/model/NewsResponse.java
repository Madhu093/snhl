package com.example.kurapma.snhl.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kurapma on 1/19/17.
 */

public class NewsResponse {

    @SerializedName("result")
    private List<NewsData> result;

    public List<NewsData> getResult() {
        return result;
    }

    public void setResult(List<NewsData> result) {
        this.result = result;
    }
}
