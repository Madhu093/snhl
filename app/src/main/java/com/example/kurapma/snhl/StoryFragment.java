package com.example.kurapma.snhl;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kurapma on 1/11/17.
 */

public class StoryFragment extends Fragment {

    public StoryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_story, container, false);
        initViews(rootview);
        return rootview;
    }

    private void initViews(View view){

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.story_recycler_view);

        StoryAdapter adapter = new StoryAdapter(getActivity(), StoryData.getData());

        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView.setLayoutManager(llm);
    }
}
