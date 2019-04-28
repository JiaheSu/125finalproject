package com.example.a125finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.DownloadManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Text;


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

                } catch(JSONException e) {
                    System.out.println("Error message is : " + e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("something");
            }
        }
        );
        queue.add(jor);
    }

}
