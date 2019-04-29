package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {
    private Button buttonCamera;
    private Button buttonFinish;
    private ImageView imageViewToL;
    private ImageView imageViewToR;
    private ImageView imageViewDoL;
    private ImageView imageViewDoR;
    private Intent intentI;
    private List<Intent> list = new ArrayList<>();
    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        imageViewToL = findViewById(R.id.imageViewToL);
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentI = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentI.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentI, REQUEST_IMAGE_CAPTURE);
                }
                list.add(intentI);
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                Bundle extras = list.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageViewToL.setImageBitmap(imageBitmap);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
