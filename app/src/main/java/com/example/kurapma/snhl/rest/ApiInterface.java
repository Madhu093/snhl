package com.example.kurapma.snhl.rest;

/**
 * Created by kurapma on 1/19/17.
 */

import com.example.kurapma.snhl.model.LocationResponse;
import com.example.kurapma.snhl.model.NewsResponse;
import com.example.kurapma.snhl.model.StoryResponse;
import com.example.kurapma.snhl.model.quotes.QuoteOfTheDay;
import com.example.kurapma.snhl.model.youTubePojo.ChannelResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET("News.json")
    Call<NewsResponse> getNewsData();

    @GET("News.json")
    Call<StoryResponse> getStoryData();

    @GET("")
    Call<QuoteOfTheDay> getQuoteOfTheDay(@Url String url);

    @POST("geolocate")
    Call<LocationResponse> getLocation(@Query("key") String key);

    @GET("")
    Call<ChannelResponse> getYouTubeChannelResponse(@Query("contentDetails") String contentDetails,@Query("forUsername") String forUsername);
}
