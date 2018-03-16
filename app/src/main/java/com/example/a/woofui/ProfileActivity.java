package com.example.a.woofui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a.api.ApiVolley;
import com.example.a.model.OwnerDetails;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;
    private ImageView image;
    private Bitmap theBitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = findViewById(R.id.profile_image_ref);

        pInitInstances();
    }

    private void pInitInstances() {
        toolBar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.profile_drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.openProfile, R.string.closeProfile);
        drawerLayout.addDrawerListener(mToggle);
        navigation = (NavigationView) findViewById(R.id.profile_navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.Home:
                        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(home);
                        break;
                    case R.id.logout:
                        Intent logout = new Intent(getApplicationContext(), SignIn.class);
                        startActivity(logout);
                        Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.DogMate:
                        Intent dogMate = new Intent(getApplicationContext(), MateActivity.class);
                        startActivity(dogMate);
                        break;
                    case R.id.DogWalk:
                        Intent dogWalk = new Intent(getApplicationContext(), WalkActivity.class);
                        startActivity(dogWalk);
                        break;
                    case R.id.history:
                        Intent history = new Intent(getApplicationContext(), HistoryActivity.class);
                        startActivity(history);
                        break;
                    case R.id.profile:
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggle.syncState();
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(this, ProfileEditActivity.class);
        Bundle extras = new Bundle();
        TextView profileName = (TextView) findViewById(R.id.profile_edit_name);
        TextView profileAddress = (TextView) findViewById(R.id.profile_edit_address);
        TextView profileEmail = (TextView) findViewById(R.id.profile_edit_email);
        TextView profileMobile = (TextView) findViewById(R.id.profile_edit_mobile);
        extras.putString("EXTRA_NAME", (String) profileName.getText());
        extras.putString("EXTRA_ADDRESS", (String) profileAddress.getText());
        extras.putString("EXTRA_EMAIL", (String) profileEmail.getText());
        extras.putString("EXTRA_MOBILE", (String) profileMobile.getText());
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void setProfileDetails(JSONObject resp) {
        TextView profileName = (TextView) findViewById(R.id.profile_edit_name);
        TextView profileAddress = (TextView) findViewById(R.id.profile_edit_address);
        TextView profileEmail = (TextView) findViewById(R.id.profile_edit_email);
        TextView profileMobile = (TextView) findViewById(R.id.profile_edit_mobile);


        //encode image to base64 string


        //decode base64 string to image


        String name = resp.optString("name");
        String address = resp.optString("address");
        String email = resp.optString("ownerEmail").isEmpty() ? "N/A" : resp.optString("ownerEmail");
        String mobile = resp.optString("ownerMobile").isEmpty() ? "N/A" : resp.optString("ownerMobile");
        profileName.setText(name);
        profileAddress.setText(address);
        profileEmail.setText(email);
        profileMobile.setText(mobile);
        // profileMobile.setText(mobile);
    }

    @Override
    public void onResume() {

        SharedPreferences shared = getSharedPreferences("UserObject",MODE_PRIVATE);
        int id = shared.getInt("ownerId",0);
        super.onResume();
        ApiVolley api = new ApiVolley(getApplicationContext());
        OwnerDetails details = new OwnerDetails(id);

        api.getOwnerDetails(this, details);
        String urlForPic = getString(R.string.picDownload_api) + "/"+id;

        Picasso.with(this).load(urlForPic).networkPolicy(NetworkPolicy.NO_CACHE).into(image);

//
//        Glide.with(this)
//                .load(urlForPic)
//                .skipMemoryCache(true)
//                .into(image);

//        Glide.with(this)
//                .asBitmap()
//
//                .load(urlForPic)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                        image.setImageBitmap(resource);
//                    }
//                });
    }


}
