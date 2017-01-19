package com.example.kurapma.snhl;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kurapma on 1/11/17.
 */

public class StoryFragment extends Fragment {

    private View rootView;
    private RecyclerView rv;
    private StoryResponse storyResponse;
    public StoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_story, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.story_recycler_view);
        rv.setHasFixedSize(true);

        storyResponse = new StoryResponse();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<StoryResponse> call = apiService.getStoryData();
        call.enqueue(new Callback<StoryResponse>() {
            @Override
            public void onResponse(Call<StoryResponse>call, Response<StoryResponse> response) {
                List<StoryData> storyDataList = response.body().getResult();
                storyResponse.setResult(storyDataList);
                rv.setAdapter(new StoryAdapter(storyDataList,getActivity()));
            }

            @Override
            public void onFailure(Call<StoryResponse>call, Throwable t) {
            }
        });

        return rootView;
    }
}
