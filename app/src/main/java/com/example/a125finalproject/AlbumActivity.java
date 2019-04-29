package com.example.a125finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AlbumActivity extends AppCompatActivity {
    private ImageView imageView1st;
    private ImageView imageView2nd;
    private ImageView imageView3rd;
    private ImageView imageView4th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Intent intent1 = getIntent();
        Bitmap bit1 = intent1.getParcelableExtra("1stPic");
        try {
            imageView1st.setImageBitmap(bit1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Intent intent2 = getIntent();
        Bitmap bit2 = intent2.getParcelableExtra("2ndPic");
        try {
            imageView2nd.setImageBitmap(bit2);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Intent intent3 = getIntent();
        Bitmap bit3 = intent3.getParcelableExtra("3rdPic");
        try {
            imageView3rd.setImageBitmap(bit3);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Intent intent4 = getIntent();
        Bitmap bit4 = intent4.getParcelableExtra("4thPic");
        try {
            imageView4th.setImageBitmap(bit4);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
