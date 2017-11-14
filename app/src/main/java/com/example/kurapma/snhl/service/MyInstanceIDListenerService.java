package com.example.kurapma.snhl.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by kurapma on 1/13/17.
 */

public class MyInstanceIDListenerService extends FirebaseInstanceIdService{
    private static final String TAG = "MyFirebaseIDService";
    private static final String HELPING_HANDS = "friendly message";

    /**
     * The Application's current Instance ID token is no longer valid
     * and thus a new one must be requested.
     */
    @Override
    public void onTokenRefresh() {
        // If you need to handle the generation of a token, initially or
        // after a refresh this is where you should do that.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token: " + token);
        // Once a token is generated, we subscribe to topic.
    }
}
