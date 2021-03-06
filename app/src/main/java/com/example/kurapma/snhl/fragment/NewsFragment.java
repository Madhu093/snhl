package com.example.kurapma.snhl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kurapma.snhl.rest.ApiClient;
import com.example.kurapma.snhl.rest.ApiInterface;
import com.example.kurapma.snhl.adapter.NewsAdapter;
import com.example.kurapma.snhl.R;
import com.example.kurapma.snhl.model.NewsData;
import com.example.kurapma.snhl.model.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kurapma on 1/11/17.
 */

public class NewsFragment extends Fragment {

    private View rootView;
    private RecyclerView rv;
    private NewsResponse newsResponse;
    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.news_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        newsResponse = new NewsResponse();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<NewsResponse> call = apiService.getNewsData();
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse>call, Response<NewsResponse> response) {
                List<NewsData> newsDataList = response.body().getResult();
                newsResponse.setResult(newsDataList);
                rv.setAdapter(new NewsAdapter(newsDataList,getActivity()));
            }

            @Override
            public void onFailure(Call<NewsResponse>call, Throwable t) {

            }
        });

        return rootView;
    }
}
