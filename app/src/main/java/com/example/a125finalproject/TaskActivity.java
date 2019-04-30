package com.example.a125finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.FileOutputStream;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
import edmt.dev.edmtdevcognitivevision.Contract.Caption;
import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;
import edmt.dev.edmtdevcognitivevision.VisionServiceClient;
import edmt.dev.edmtdevcognitivevision.VisionServiceRestClient;

public class TaskActivity extends AppCompatActivity {
    private final static String API_KEY = "6bf2823f8f9940ad95a84f981a9f8bab";
    private final static String API_LINK = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";
    private Button buttonCamera;
    private Button buttonFinish;
    private ImageView imageViewToL;
    private ImageView imageViewToR;
    private ImageView imageViewDoL;
    private ImageView imageViewDoR;
    private Intent intent;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
                startActivity(intent);
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

        Azure_Analysis();
    }
    public void Azure_Analysis() {

        //Declare Vision Client
        final VisionServiceClient visionServiceClient = new VisionServiceRestClient(API_KEY, API_LINK);

        //Get bitmap and add to ImageView
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.starrynight);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        //Use async task to request API
        AsyncTask<InputStream,String,String> visionTask = new AsyncTask<InputStream, String, String>() {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            @Override
            protected void onPreExecute() { progressBar.setVisibility(View.VISIBLE); }

            @Override
            protected String doInBackground(InputStream... inputStreams) {
                try {
                    publishProgress("Recognizing...");
                    String[] features = {"description"}; //Get Description from API Return Result
                    String[] details = {};

                    AnalysisResult result = visionServiceClient.analyzeImage(inputStreams[0], features, details);
                    String jsonResult = new Gson().toJson(result);
                    return jsonResult;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (VisionServiceException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(TextUtils.isEmpty(s)) {
                    Toast.makeText(TaskActivity.this, "API return empty result", Toast.LENGTH_SHORT);
                } else {
                    //progressDialog.dismiss();

                    AnalysisResult result = new Gson().fromJson(s, AnalysisResult.class);
                    StringBuilder result_Text = new StringBuilder();
                    for (Caption caption : result.description.captions) {
                        result_Text.append(caption.text);
                        System.out.println("here");
                    }
                }
            }

            @Override
            protected void onProgressUpdate(String... values) {
                //progressBar.settext(values[0]);
            }
        };

        //Run task
        visionTask.execute(inputStream);
    }
}
