package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TaskActivity extends AppCompatActivity {
    private Button buttonCamera;
    private Button buttonFinish;
    private ImageView imageViewToL;
    private ImageView imageViewToR;
    private ImageView imageViewDoL;
    private ImageView imageViewDoR;
    private Intent intentI;
    private Intent intentII;
    private Intent intentIII;
    private Intent intentIV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        imageViewToL = findViewById(R.id.imageViewToL);
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentI = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentI.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentI, 1);
                }
                intentII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentII, 2);
                }
                intentIII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentIII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentIII, 3);
                }
                intentIV = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentIV.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentIV, 4);
                }
            }
        });

        buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TaskActivity.this, AlbumActivity.class);
                startActivity(intent2);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent list) {
        super.onActivityResult(requestCode, resultCode, list);
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Bundle extras = list.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageViewToL.setImageBitmap(bmp);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageViewToR.setImageBitmap(bmp);
                }
                break;
            case 3:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageViewDoL.setImageBitmap(bmp);
                }
                break;
            case 4:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageViewDoR.setImageBitmap(bmp);
                }
                break;
        }
    }
}
