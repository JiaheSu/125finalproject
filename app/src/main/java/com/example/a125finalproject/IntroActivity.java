package com.example.a125finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {
    private ImageButton imageButtonTL;
    private TextView textViewTL;
    private ImageButton imageButtonTR;
    private TextView textViewTR;
    private ImageButton imageButtonDL;
    private TextView textViewDL;
    private ImageButton imageButtonDR;
    private TextView textViewDR;

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

        textViewTL = findViewById(R.id.textViewTL);

        imageButtonTR = findViewById(R.id.imageButtonTR);
        imageButtonTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        textViewTR = findViewById(R.id.textViewTR);

        imageButtonDL = findViewById(R.id.imageButtonDL);
        imageButtonDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        textViewDL = findViewById(R.id.textViewDL);

        imageButtonDR = findViewById(R.id.imageButtonDR);
        imageButtonDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });

        textViewDR = findViewById(R.id.textViewDR);
    }
}
