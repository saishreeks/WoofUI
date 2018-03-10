package com.example.a.woofui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a.api.ApiVolley;
import com.example.a.model.OwnerDetails;
import com.example.a.model.WalkInfo;

import java.io.FileDescriptor;
import java.io.IOException;

public class ProfileEditActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;
    private Button buttonEditPhoto;
    private static int RESULT_LOAD_IMAGE = 1;

    EditText profileEditName;
    EditText profileEditAddress;
    EditText profileEditEmail;
    EditText profileEditMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
         profileEditName = (EditText) findViewById(R.id.edit_name);
         profileEditAddress = (EditText) findViewById(R.id.edit_address);
         profileEditEmail = (EditText) findViewById(R.id.edit_email);
         profileEditMobile = (EditText) findViewById(R.id.edit_mobile);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name_string = extras.getString("EXTRA_NAME");
        String address_string = extras.getString("EXTRA_ADDRESS");
        String email_string = extras.getString("EXTRA_EMAIL");
        String mobile_string = extras.getString("EXTRA_MOBILE");


        profileEditName.setText(name_string);
        profileEditAddress.setText(address_string);
        profileEditEmail.setText(email_string);
        profileEditMobile.setText(mobile_string);

        buttonEditPhoto = (Button) findViewById(R.id.edit_photo_button);
    }

    public void selectImage(View view) {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.profile_image_ref);

            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            imageView.setImageBitmap(bmp);

        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }


    private Bitmap getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String filePath = cursor.getString(column_index);
        // cursor.close();
        // C    onvert file path into bitmap image using below line.
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        return bitmap;
    }

    public void saveProfile(View view) {
            ApiVolley api = new ApiVolley(getApplicationContext());
        OwnerDetails ownerDetails = new OwnerDetails();

            ownerDetails.setName(getValueFromUI(profileEditName));
            ownerDetails.setAddress(getValueFromUI(profileEditAddress));
            ownerDetails.setOwnerEmail(getValueFromUI(profileEditEmail));
            ownerDetails.setOwnerMobile(getValueFromUI(profileEditMobile));
            api.editOwnerDetails(this,ownerDetails );


    }

    public String getValueFromUI(EditText fieldName){
        return fieldName.getText()==null ? null :String.valueOf(fieldName.getText());
    }


}





