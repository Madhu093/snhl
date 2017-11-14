package com.example.kurapma.snhl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurapma.snhl.R;
import com.example.kurapma.snhl.model.GetBitmap;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by kurapma on 1/18/17.
 */

public class SelectedStoryActivity extends AppCompatActivity {

    private TextView tv_title, tv_text;
    private ImageView imageView;
    private String getImageURL, title, text;
    private GetBitmap bitmap;
    private AdView mAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_story);
        tv_title = (TextView) findViewById(R.id.selected_story_title);
        tv_text = (TextView) findViewById(R.id.selected_story_text);
        imageView = (ImageView) findViewById(R.id.selected_story_image_view);
        bitmap = new GetBitmap(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        if(mAdView.getAdSize() != null || mAdView.getAdUnitId() != null){
            mAdView.loadAd(adRequest);
        }

        if (getIntent().hasExtra("ImageURL")) {
            Intent intent = getIntent();
            getImageURL = intent.getStringExtra("ImageURL");
            Bitmap imageBitmap = bitmap.getImage(getImageURL);
            imageView.setImageBitmap(imageBitmap);
        }

        if (getIntent().hasExtra("Title")) {
            Intent intent = getIntent();
            title = intent.getStringExtra("Title");
            tv_title.setText(title.toUpperCase());
        }

        if (getIntent().hasExtra("Text")) {
            Intent intent = getIntent();
            text = intent.getStringExtra("Text");
            tv_text.setText(text);
        }

    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
