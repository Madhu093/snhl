package com.example.kurapma.snhl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

/**
 * Created by kurapma on 1/13/17.
 */

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private TextView mUsername;
    private TextView mUserEmail;
    private ImageView mPhoto;
    private String mPhotoURL = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUsername = (TextView) findViewById(R.id.tvProfileName);
        mUserEmail = (TextView) findViewById(R.id.tvEmail);
        mPhoto = (ImageView) findViewById(R.id.mPhoto);

        if (mFirebaseUser == null) {

        } else {
            String facebookUserId = "";
            String userName = "";
            String emailId = "";
            for (UserInfo profile : mFirebaseUser.getProviderData()) {
                if (profile.getProviderId().equals(getString(R.string.facebook_provider_id))) {
                    facebookUserId = profile.getUid();
                    userName = profile.getDisplayName();
                    emailId = profile.getEmail();
                    mPhotoURL = "https://graph.facebook.com/" + facebookUserId + "/picture?type=large";
                } else {
                    mPhotoURL = mFirebaseUser.getPhotoUrl().toString();
                    mPhotoURL = mPhotoURL.replace("/s96-c/", "/s300-c/");
                    userName = mFirebaseUser.getDisplayName();
                    emailId = mFirebaseUser.getEmail();
                }
                Picasso.with(this).load(mPhotoURL).into(mPhoto);
                mUsername.setText(userName);
                mUserEmail.setText(emailId);
            }
        }

    }

}
