package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
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
    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskActivity.this, AlbumActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
                startActivity(intent);
            }
        });

        imageViewToL = findViewById(R.id.imageViewToL);
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageViewToL.setImageBitmap(imageBitmap);
            }
        }

        imageViewToR = findViewById(R.id.imageViewToR);

        imageViewDoL = findViewById(R.id.imageViewDoL);

        imageViewDoR = findViewById(R.id.imageViewDoR);
    }
}
