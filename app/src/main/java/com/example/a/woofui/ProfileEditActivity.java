package com.example.a.woofui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.OwnerDetails;
import com.example.a.model.WalkInfo;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

public class ProfileEditActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;

    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;
    private Button buttonEditPhoto;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap image;
    Bitmap bmp;

    EditText profileEditName;
    EditText profileEditAddress;
    EditText profileEditEmail;
    EditText profileEditMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        pInitInstances();
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

    private void pInitInstances() {
        toolBar = (Toolbar) findViewById(R.id.toolbar_profile_edit);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.profile_edit_drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.openProfile, R.string.closeProfile);
        drawerLayout.addDrawerListener(mToggle);
        navigation = (NavigationView) findViewById(R.id.profile_edit_navigation);
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
                    case R.id.history:
                        Intent history = new Intent(getApplicationContext(), HistoryActivity.class);
                        startActivity(history);
                        break;
                    case R.id.profile:
                        Intent profile = new Intent(getApplicationContext(), ProfileActivity.class);
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

            bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
                bmp = imageRotation(picturePath,bmp);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            imageView.setImageBitmap(bmp);


        }
    }

    public Bitmap imageRotation(String photoPath, Bitmap bitmap) {
        ExifInterface ei = null;
        try {
            ei = new ExifInterface(photoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch(orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmap;
        }
        return rotatedBitmap;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
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


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        ownerDetails.setProfilepic(imageString);
        api.editOwnerDetails(this, ownerDetails);
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               finish();
           }
       },4000);
    }

    public String getValueFromUI(EditText fieldName) {
        return fieldName.getText() == null ? null : String.valueOf(fieldName.getText());
    }


}





