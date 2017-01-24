package com.example.kurapma.snhl.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurapma.snhl.R;
import com.example.kurapma.snhl.adapter.PagerAdapter;
import com.example.kurapma.snhl.model.Config;
import com.example.kurapma.snhl.model.LocationResponse;
import com.example.kurapma.snhl.rest.ApiInterface;
import com.example.kurapma.snhl.rest.LocationClient;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private static final int REQUEST_INVITE = 500;
    private String TAG = "MainActivity";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView mHeaderLogo;
    private TextView mHeaderTitle;
    private TextView mHeaderSubTitle;
    private String mPhotoURL = "";
    private MenuItem mAuthenticateMenuItem, mDonate;
    private NavigationView navigationView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.news));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.stories));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.donors));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(2);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        if (mFirebaseUser == null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            return;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateNavigationView();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void initialize() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(R.string.app_name);
                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    navigationView.getMenu().getItem(i).setChecked(false);
                }
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.profile);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView = navigationView.getHeaderView(0);
        hView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

            }
        });

        Menu menu = navigationView.getMenu();
        mAuthenticateMenuItem = menu.findItem(R.id.logout);
        mDonate = menu.findItem(R.id.donate);

        mHeaderLogo = (ImageView) hView.findViewById(R.id.profile_logo);
        mHeaderTitle = (TextView) hView.findViewById(R.id.profile_name);
        mHeaderSubTitle = (TextView) hView.findViewById(R.id.profile_email);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.donate:
                startActivity(new Intent(this, DonateActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.experiment:
                experiment();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.videos:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, VideoActivity.class));
                return true;
            case R.id.assistance:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.share:
                share();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.confirm_logout);
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            navigationView.getMenu().findItem(R.id.logout).setChecked(false);
                            AsyncTask.execute(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      mFirebaseAuth.signOut();
                                                      LoginManager.getInstance().logOut();
                                                      Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                                                  }
                                              }
                            );
                        } catch (Exception e) {

                        }
                        mFirebaseUser = null;
                        Intent launchSingInActivity = new Intent(MainActivity.this, MainActivity.class);
                        launchSingInActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchSingInActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(launchSingInActivity);
                    }
                });
                builder.setNegativeButton(android.R.string.no, null);
                builder.show();
                return true;
        }

        return true;
    }

    private void populateNavigationView() {
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
                    userName = mFirebaseUser.getDisplayName();
                    emailId = mFirebaseUser.getEmail();
                }
                Picasso.with(getApplicationContext()).load(mPhotoURL).into(mHeaderLogo);
                mHeaderTitle.setText(userName);
                mHeaderSubTitle.setText(emailId);
            }
        }
    }

    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivityForResult(Intent.createChooser(sharingIntent, "Share via"), REQUEST_INVITE);
        sendToFirebaseAnalytics();
    }

    private void sendToFirebaseAnalytics() {
        Bundle payload = new Bundle();
        payload.putString(FirebaseAnalytics.Param.VALUE, "sent");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE,
                payload);
    }

    private void experiment(){
        ApiInterface locationClient =
                LocationClient.getClient().create(ApiInterface.class);

        Call<LocationResponse> call = locationClient.getLocation(Config.GOOGLE_API_KEY);
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                System.out.println("response"+ response.body());
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
            }
        });
    }

}


