package com.example.kurapma.snhl.model;

import android.graphics.Bitmap;

/**
 * Created by kurapma on 1/18/17.
 */

public class StoryData {

    private String name;
    private String title;
    private String url;
    private Bitmap image;

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
