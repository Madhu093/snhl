package com.example.kurapma.snhl.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kurapma.snhl.R;

/**
 * Created by kurapma on 1/12/17.
 */

public class DonorFragment extends Fragment {

    public DonorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_donor, container, false);
        return rootView;
    }
}
