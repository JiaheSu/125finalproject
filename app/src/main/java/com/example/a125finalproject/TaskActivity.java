package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private Intent intentII;
    private Intent intentIII;
    private Intent intentIV;
    private Uri imageUriI;
    private Uri imageUriII;
    private Uri imageUriIII;
    private Uri imageUriIV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        imageViewToL = findViewById(R.id.imageViewToL);
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputI = new File(getExternalCacheDir(), "output_I.jpg");
                try {
                    if (outputI.exists()) {
                        outputI.delete();
                    }
                    outputI.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUriI = FileProvider.getUriForFile(TaskActivity.this,
                            "com.example.cameraalbum.fileprovider", outputI);
                } else {
                    imageUriI = Uri.fromFile(outputI);
                }

                File outputII = new File(getExternalCacheDir(), "output_II.jpg");
                try {
                    if (outputII.exists()) {
                        outputII.delete();
                    }
                    outputII.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUriII = FileProvider.getUriForFile(TaskActivity.this,
                            "com.example.cameraalbum.fileprovider", outputII);
                } else {
                    imageUriII = Uri.fromFile(outputII);
                }

                File outputIII = new File(getExternalCacheDir(), "output_III.jpg");
                try {
                    if (outputIII.exists()) {
                        outputIII.delete();
                    }
                    outputIII.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUriIII = FileProvider.getUriForFile(TaskActivity.this,
                            "com.example.cameraalbum.fileprovider", outputIII);
                } else {
                    imageUriIII = Uri.fromFile(outputIII);
                }

                File outputIV = new File(getExternalCacheDir(), "output_IV.jpg");
                try {
                    if (outputIV.exists()) {
                        outputIV.delete();
                    }
                    outputIV.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUriIV = FileProvider.getUriForFile(TaskActivity.this,
                            "com.example.cameraalbum.fileprovider", outputIV);
                } else {
                    imageUriIV = Uri.fromFile(outputIV);
                }

                intentI = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                intentI.putExtra(MediaStore.EXTRA_OUTPUT, imageUriI);
                if (intentI.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentI, 1);
                }
                intentII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                intentII.putExtra(MediaStore.EXTRA_OUTPUT, imageUriII);
                if (intentII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentII, 2);
                }
                intentIII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                intentIII.putExtra(MediaStore.EXTRA_OUTPUT, imageUriIII);
                if (intentIII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentIII, 3);
                }
                intentIV = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                intentIV.putExtra(MediaStore.EXTRA_OUTPUT, imageUriIV);
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
                    try {
                        Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUriI));
                        imageViewToL.setImageBitmap(bmp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                if(resultCode == RESULT_OK) {
                    try {
                        Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUriII));
                        imageViewToR.setImageBitmap(bmp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                if(resultCode == RESULT_OK) {
                    try {
                        Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUriIII));
                        imageViewDoL.setImageBitmap(bmp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 4:
                if(resultCode == RESULT_OK) {
                    try {
                        Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUriIV));
                        imageViewDoR.setImageBitmap(bmp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
