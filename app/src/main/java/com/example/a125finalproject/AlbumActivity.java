package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileInputStream;

public class AlbumActivity extends AppCompatActivity {
    private ImageView imageView1st;
    private ImageView imageView2nd;
    private ImageView imageView3rd;
    private ImageView imageView4th;
    private Bitmap bmp1;
    private Bitmap bmp2;
    private Bitmap bmp3;
    private Bitmap bmp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        String filename1 = getIntent().getStringExtra("image1");
        try {
            FileInputStream is = this.openFileInput(filename1);
            bmp1 = BitmapFactory.decodeStream(is);
            imageView1st.setImageBitmap(bmp1);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filename2 = getIntent().getStringExtra("image2");
        try {
            FileInputStream is = this.openFileInput(filename2);
            bmp2 = BitmapFactory.decodeStream(is);
            imageView2nd.setImageBitmap(bmp2);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filename3 = getIntent().getStringExtra("image3");
        try {
            FileInputStream is = this.openFileInput(filename3);
            bmp3 = BitmapFactory.decodeStream(is);
            imageView3rd.setImageBitmap(bmp3);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filename4 = getIntent().getStringExtra("image4");
        try {
            FileInputStream is = this.openFileInput(filename4);
            bmp4 = BitmapFactory.decodeStream(is);
            imageView4th.setImageBitmap(bmp4);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
