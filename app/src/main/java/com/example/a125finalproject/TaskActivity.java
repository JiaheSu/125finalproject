package com.example.a125finalproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
/*
import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
import edmt.dev.edmtdevcognitivevision.Contract.Caption;
import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;
import edmt.dev.edmtdevcognitivevision.VisionServiceClient;
import edmt.dev.edmtdevcognitivevision.VisionServiceRestClient;
*/

public class TaskActivity extends AppCompatActivity {

    public static final String TAG = "MYTAG";
    private RequestQueue QUEUE;
    private String URLHTTP = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";
    private String APIkey = "6bf2823f8f9940ad95a84f981a9f8bab";
    //later will be changed into the bitmap sent to API - starrynight is an example for testing
    public Bitmap bitmap;
    private String task = "yellow";

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
        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.starrynight);

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

        QUEUE = Volley.newRequestQueue(TaskActivity.this);
        httpGET(URLHTTP, bitmap);
    }


    /**
     * Convert an image to a byte array, upload to the Microsoft Cognitive Services API,
     * and return a result.
     *
     * @param currentBitmap the bitmap to process
     * @return unused result
     * */

     public void httpGET(String url, Bitmap currentBitmap) {
         /*
          * Convert the image from a Bitmap to a byte array for upload.
          * */

         final ByteArrayOutputStream stream = new ByteArrayOutputStream();
         currentBitmap.compress(Bitmap.CompressFormat.PNG,
                 100, stream);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    //parseJson(response);
                }
                Log.d(TAG, "RESPONSE FROM SERVER:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    Log.d(TAG, "ERROR" + responseBody);
                } catch (UnsupportedEncodingException errorr) {
                    Log.d(TAG, errorr.toString());
                }
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Set up headers properly
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/octet-stream");
                headers.put("Ocp-Apim-Subscription-Key", APIkey);
                return headers;
            }
            @Override
            public String getBodyContentType() {
                // Set the body content type properly for a binary upload
                return "application/octet-stream";
            }
            @Override
            public byte[] getBody() {
                return stream.toByteArray();
            }
        };
        QUEUE.add(stringRequest);

    }
    public void parseJson(String response) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
        JsonObject description = json.getAsJsonObject("description");
        JsonArray tags = description.getAsJsonArray("tags");
        for (JsonElement n: tags) {
            if (n.getAsString().equals(task)) {
                imageViewDoL.setImageResource(R.drawable.starrynight);
            }
        }

    }

}
