package com.example.a125finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class IntroActivity extends AppCompatActivity {
    private ImageButton imageButtonTL;
    private ImageButton imageButtonTR;
    private ImageButton imageButtonDL;
    private ImageButton imageButtonDR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        imageButtonTL = findViewById(R.id.imageButtonTL);

        imageButtonTR = findViewById(R.id.imageButtonTR);

        imageButtonDL = findViewById(R.id.imageButtonDL);

        imageButtonDR = findViewById(R.id.imageButtonDR);
    }
}
