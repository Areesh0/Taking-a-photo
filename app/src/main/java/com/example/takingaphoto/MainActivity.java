package com.example.takingaphoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
// initializing variables
    private ImageView mimageView;
    private static final int REQUEST_IMAGE_CAPTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mimageView = findViewById(R.id.imageView);
    }

    public void takePhoto(View view) {
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //creating an intent object to capture an image

        if(imageTakeIntent.resolveActivity(getPackageManager()) != null){ //check if there is an application capable of handling this intent
            startActivityForResult(imageTakeIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override  //to receive the result from the other application
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data"); //get bitmap from extras
            mimageView.setImageBitmap(imageBitmap); //display the image 
        }
    }
}