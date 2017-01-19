package com.example.kurapma.snhl;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kurapma on 1/19/17.
 */

public class StoryResponse {
    @SerializedName("result")
    private List<StoryData> result;

    public List<StoryData> getResult() {
        return result;
    }

    public void setResult(List<StoryData> result) {
        this.result = result;
    }
}
