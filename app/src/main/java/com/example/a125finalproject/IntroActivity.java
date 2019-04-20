package com.example.a125finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        imageButtonTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        imageButtonTR = findViewById(R.id.imageButtonTR);
        imageButtonTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        imageButtonDL = findViewById(R.id.imageButtonDL);
        imageButtonDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        imageButtonDR = findViewById(R.id.imageButtonDR);
        imageButtonDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
