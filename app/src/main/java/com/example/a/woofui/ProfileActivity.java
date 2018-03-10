package com.example.a.woofui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a.api.ApiVolley;
import com.example.a.model.OwnerDetails;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApiVolley api=new ApiVolley(getApplicationContext());
        OwnerDetails details=new OwnerDetails();
        api.getOwnerDetails(this,details);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }

    public void goToProfile(View view){
        Intent intent = new Intent(this, ProfileEditActivity.class);
        Bundle extras = new Bundle();
        TextView profileName =(TextView) findViewById(R.id.profile_edit_name);
        TextView profileAddress =(TextView) findViewById(R.id.profile_edit_address);
        TextView profileEmail =(TextView) findViewById(R.id.profile_edit_email);
        TextView profileMobile =(TextView) findViewById(R.id.profile_edit_mobile);
        extras.putString("EXTRA_NAME", (String) profileName.getText());
        extras.putString("EXTRA_ADDRESS", (String) profileAddress.getText());
        extras.putString("EXTRA_EMAIL", (String) profileEmail.getText());
        extras.putString("EXTRA_MOBILE", (String) profileMobile.getText());
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void setProfileDetails(JSONObject resp)
    {
        TextView profileName =(TextView) findViewById(R.id.profile_edit_name);
        TextView profileAddress =(TextView) findViewById(R.id.profile_edit_address);
        TextView profileEmail =(TextView) findViewById(R.id.profile_edit_email);
        TextView profileMobile =(TextView) findViewById(R.id.profile_edit_mobile);
        String name = resp.optString("name");
        String address = resp.optString("address");
        String email = resp.optString("ownerEmail").isEmpty()? "N/A" : resp.optString("ownerEmail");
        String mobile = resp.optString("ownerMobile").isEmpty()? "N/A" : resp.optString("ownerMobile");
        profileName.setText(name);
        profileAddress.setText(address);
        profileEmail.setText(email);
        profileMobile.setText(mobile);
       // profileMobile.setText(mobile);
    }


}