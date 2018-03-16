package com.example.a.woofui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDogInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText dogName;
    private EditText dogBreed;
    private EditText txtAge;
    private String gender;
    private Button submit;
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog_info);
        submit=(Button) findViewById(R.id.btn_Register);
        submit.setOnClickListener(this);
    }

    public void showresp()
    {
        Toast.makeText(AddDogInfoActivity.this, "Registered Successfully" ,Toast.LENGTH_SHORT).show();
    }

    public void showerror()
    {
        Toast.makeText(AddDogInfoActivity.this, "Error Occured" ,Toast.LENGTH_SHORT).show();
    }





    @Override
    public void onClick(View view) {
        ApiVolley api=new ApiVolley(getApplicationContext());
        dogName = (EditText)findViewById(R.id.txtDogName);
        dogBreed = (EditText) findViewById(R.id.txtBreed);
        txtAge = (EditText) findViewById(R.id.txtAge);
        DogDetails details = new DogDetails();
        details.setName(dogName.getText().toString());
        try {
            details.setDob(formatter.parse(txtAge.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //details.setAge(Integer.parseInt(txtAge.getText().toString()));
        details.setBreed(dogBreed.getText().toString());
       details.setGender(gender);
        api.postDogDetails(this,details);

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonMale:
                if (checked)
                    gender = "Male";
                    break;
            case R.id.radioButtonFemale:
                if (checked)
                    gender = "Female";
                    break;
        }
    }

}
