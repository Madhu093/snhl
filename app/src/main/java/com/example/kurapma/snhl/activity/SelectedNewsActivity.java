package com.example.kurapma.snhl.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurapma.snhl.R;
import com.example.kurapma.snhl.model.GetBitmap;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
 * Created by kurapma on 1/12/17.
 */

public class SelectedNewsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;
    private TextView tv_title, tv_text;
    private ImageView imageView;
    private String getImageURL, title, text;
    private GetBitmap bitmap;
    private AdView mAdView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_news);
        tv_title = (TextView) findViewById(R.id.selected_news_title);
        tv_text = (TextView) findViewById(R.id.selected_news_text);
        imageView = (ImageView) findViewById(R.id.selected_news_image_view);
        bitmap = new GetBitmap(this);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (getIntent().hasExtra("ImageURL")) {
            Intent intent = getIntent();
            getImageURL = intent.getStringExtra("ImageURL");
            Picasso.with(getApplicationContext()).load(getImageURL).resize(1080, 760).into(imageView);
            /*Bitmap imageBitmap = bitmap.getImage(getImageURL);
            imageView.setImageBitmap(imageBitmap);*/
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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
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
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
