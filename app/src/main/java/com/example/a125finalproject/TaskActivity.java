package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class TaskActivity extends AppCompatActivity {
    private Button buttonCamera;
    private Button buttonFinish;
    private ImageView imageViewToL;
    private ImageView imageViewToR;
    private ImageView imageViewDoL;
    private ImageView imageViewDoR;
    private List<Intent> list;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    list.add(intent);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
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

        imageViewToL = findViewById(R.id.imageViewToL);
        imageViewToR = findViewById(R.id.imageViewToR);
        imageViewDoL = findViewById(R.id.imageViewDoL);
        imageViewDoR = findViewById(R.id.imageViewDoR);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Intent intent1 = list.get(0);
            Intent intent2 = list.get(1);
            Intent intent3 = list.get(2);
            Intent intent4 = list.get(3);

            Bundle extras1 = intent1.getExtras();
            Bitmap imageBitmap1 = (Bitmap) extras1.get("data");
            imageViewToL.setImageBitmap(imageBitmap1);
            Bundle extras2 = intent2.getExtras();
            Bitmap imageBitmap2 = (Bitmap) extras2.get("data");
            imageViewToR.setImageBitmap(imageBitmap2);
            Bundle extras3 = intent3.getExtras();
            Bitmap imageBitmap3 = (Bitmap) extras3.get("data");
            imageViewDoL.setImageBitmap(imageBitmap3);
            Bundle extras4 = intent4.getExtras();
            Bitmap imageBitmap4 = (Bitmap) extras4.get("data");
            imageViewDoR.setImageBitmap(imageBitmap4);
        }
    }
}
