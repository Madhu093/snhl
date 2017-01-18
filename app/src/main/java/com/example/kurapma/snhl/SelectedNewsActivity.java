package com.example.kurapma.snhl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_news);
        tv_title = (TextView) findViewById(R.id.selected_news_title);
        tv_text = (TextView) findViewById(R.id.selected_news_text);
        imageView = (ImageView) findViewById(R.id.selected_news_image_view);
        bitmap = new GetBitmap(this);

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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
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
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
