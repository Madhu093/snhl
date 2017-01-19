package com.example.kurapma.snhl;

/**
 * Created by kurapma on 1/19/17.
 */

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("News.json")
    Call<NewsResponse> getNewsData();

    @GET("News.json")
    Call<StoryResponse> getStoryData();
}
