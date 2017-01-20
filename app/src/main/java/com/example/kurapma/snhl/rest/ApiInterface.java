package com.example.kurapma.snhl.rest;

/**
 * Created by kurapma on 1/19/17.
 */

import com.example.kurapma.snhl.model.NewsResponse;
import com.example.kurapma.snhl.model.quotes.QuoteOfTheDay;
import com.example.kurapma.snhl.model.StoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET("News.json")
    Call<NewsResponse> getNewsData();

    @GET("News.json")
    Call<StoryResponse> getStoryData();

    @GET("")
    Call<QuoteOfTheDay> getQuoteOfTheDay(@Url String url);
}
