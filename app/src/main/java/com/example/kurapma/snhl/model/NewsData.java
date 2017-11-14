package com.example.kurapma.snhl.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kurapma on 1/12/17.
 */

public class NewsData {
    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("amountNeeded")
    private int amountNeeded;

    @SerializedName("amountCollected")
    private int amountCollected;

    private Bitmap image;

    public int getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(int amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public int getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(int amountCollected) {
        this.amountCollected = amountCollected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
