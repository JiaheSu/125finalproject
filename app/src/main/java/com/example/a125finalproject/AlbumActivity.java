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
        Bundle message = intent1.getBundleExtra("1stPic");
        ImageView imageView1st = findViewById(R.id.imageView1st);
        Bitmap bmp = (Bitmap) message.get("data");
        imageView1st.setImageBitmap(bmp);

        Intent intent2 = getIntent();
        Bundle message2 = intent2.getBundleExtra("2ndPic");
        ImageView imageView2nd = findViewById(R.id.imageView2nd);
        Bitmap bmp2 = (Bitmap) message2.get("data");
        imageView2nd.setImageBitmap(bmp2);

        Intent intent3 = getIntent();
        Bundle message3 = intent3.getBundleExtra("3rdPic");
        ImageView imageView3rd = findViewById(R.id.imageView3rd);
        Bitmap bmp3 = (Bitmap) message3.get("data");
        imageView3rd.setImageBitmap(bmp3);

        Intent intent4 = getIntent();
        Bundle message4 = intent4.getBundleExtra("4thPic");
        ImageView imageView4th = findViewById(R.id.imageView4th);
        Bitmap bmp4 = (Bitmap) message4.get("data");
        imageView4th.setImageBitmap(bmp4);
    }
}
