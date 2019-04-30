package com.example.a125finalproject;

//import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.app.DownloadManager;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/*
import edmt.dev.edmtdevcognitivevision.Contract.AnalysisResult;
import edmt.dev.edmtdevcognitivevision.Contract.Caption;
import edmt.dev.edmtdevcognitivevision.Rest.VisionServiceException;
import edmt.dev.edmtdevcognitivevision.VisionServiceClient;
import edmt.dev.edmtdevcognitivevision.VisionServiceRestClient;
*/


public class MainActivity extends AppCompatActivity {
    final static int TEMP_MIN = 55;
    final static int TEMP_MAX = 85;
    private static ImageView imageView;
    private static Button buttonStart;
    private static Button buttonAlbum;
    private static TextView textViewWeather;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        buttonAlbum = findViewById(R.id.buttonAlbum);
        buttonAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(intent);
            }
        });

        //***Have changed quote into weather
        textViewWeather = findViewById(R.id.textViewWeather);

        queue = Volley.newRequestQueue(this);
        find_weather();
    }

    //A weather API which set the textViewWeather on the main page.
    public void find_weather() {
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&appid=9f17c7e37b3770ffd3356688a4cfb33c";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String description = object.getString("description");
                    String temp = String.valueOf(main_object.getDouble("temp"));

                    double temp_int = Double.parseDouble(temp);
                    double fahrenheit = (temp_int - 273 - 32) / 1.8000;
                    fahrenheit = Math.round(fahrenheit);
                    int f = (int) fahrenheit;
                    String commentOnWeather = "";
                    if (f < TEMP_MAX && f > TEMP_MIN) {
                        commentOnWeather = "It's a nice day to go around and explore!";
                    } else if (f > TEMP_MAX) {
                        commentOnWeather = "It's way too hot outside; Drink an ice cold drink while exploring!";
                    } else if (f < TEMP_MIN) {
                        commentOnWeather = "It's way too cold outside; Stay warm while exploring!";
                    }
                    commentOnWeather = description + "\n"
                            + "Current temperature: " + f + "°F"
                            + "\n" + commentOnWeather;
                    textViewWeather.setText(commentOnWeather);
                    Log.d("myDebugTag", "debug message");

                } catch(JSONException e) {
                    System.out.println("Error message is : " + e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }
        );
        queue.add(jor);
    }
}
