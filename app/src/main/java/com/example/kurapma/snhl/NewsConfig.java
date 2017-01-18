package com.example.kurapma.snhl;

import android.graphics.Bitmap;

/**
 * Created by kurapma on 1/14/17.
 */

public class NewsConfig {
    public static String[] names;
    public static String[] titles;
    public static String[] urls;
    public static Bitmap[] bitmaps;

    public static final String GET_URL = "https://snhl.000webhostapp.com/News/News.json";
    public static final String TAG_IMAGE_URL = "url";
    public static final String TAG_IMAGE_NAME = "name";
    public static final String TAG_TITLE = "title";
    public static final String TAG_JSON_ARRAY="result";

    public NewsConfig(int i){
        titles = new String[i];
        names = new String[i];
        urls = new String[i];
        bitmaps = new Bitmap[i];
    }

}
