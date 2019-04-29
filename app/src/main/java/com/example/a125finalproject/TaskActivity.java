package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TaskActivity extends AppCompatActivity {
    private ImageButton imageBToL;
    private ImageButton imageBToR;
    private ImageButton imageBDoL;
    private ImageButton imageBDoR;
    private Button buttonFinish;
    private Intent intentI;
    private Intent intentII;
    private Intent intentIII;
    private Intent intentIV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        imageBToL = findViewById(R.id.imageBToL);
        imageBToR = findViewById(R.id.imageBToR);
        imageBDoL = findViewById(R.id.imageBDoL);
        imageBDoR = findViewById(R.id.imageBDoR);
        imageBToL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentI = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentI.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentI, 1);
                }
            }
        });

        imageBToR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentII, 2);
                }
            }
        });

        imageBDoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentIII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentIII, 3);
                }
            }
        });

        imageBDoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    imageBToL.setImageBitmap(bmp);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageBToR.setImageBitmap(bmp);
                }
                break;
            case 3:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageBDoL.setImageBitmap(bmp);
                }
                break;
            case 4:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageBDoR.setImageBitmap(bmp);
                }
                break;
        }
    }
}
