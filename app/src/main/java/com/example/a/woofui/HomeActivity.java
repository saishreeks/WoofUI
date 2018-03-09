package com.example.a.woofui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initInstances();
    }

    private void initInstances(){
        toolBar = (Toolbar) findViewById(R.id.act_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.Home:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        Intent logout=new Intent(getApplicationContext(),SignIn.class);
                        startActivity(logout);
                        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.DogMate:
                        Intent dogMate = new Intent(getApplicationContext(), MateActivity.class);
                        startActivity(dogMate);
                        break;
                    case R.id.history:
                        Intent history = new Intent(getApplicationContext(),HistoryActivity.class);
                        startActivity(history);
                        break;
                    case R.id.profile:

                        Intent profile = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(profile);
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
}
