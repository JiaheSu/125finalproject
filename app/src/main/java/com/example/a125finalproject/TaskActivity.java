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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.forismastic.Forismatic;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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
    private RequestQueue queue;

    //a textview that display the quote and the number of remaining tasks
    private TextView quoteView;
    private String quoteText;
    private int numOfTask = 4;
    private Button buttonFinish;
    private ImageButton imageBToL;
    private ImageButton imageBToR;
    private ImageButton imageBDoL;
    private ImageButton imageBDoR;
    private Intent intentI;
    private Intent intentII;
    private Intent intentIII;
    private Intent intentIV;
    private Bitmap bmp1;
    private Bitmap bmp2;
    private Bitmap bmp3;
    private Bitmap bmp4;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.starrynight);

        imageBToL = findViewById(R.id.imageBToL);
        imageBToR = findViewById(R.id.imageBToR);
        imageBDoL = findViewById(R.id.imageBDoL);
        imageBDoR = findViewById(R.id.imageBDoR);
        imageBToL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentI = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentI.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentI, 1);
                }

            }
        });

        imageBToR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentII, 2);
                }
            }
        });

        imageBDoL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIII = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentIII.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentIII, 3);
                }
            }
        });

        imageBDoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIV = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentIV.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intentIV, 4);
                }
            }
        });

        buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TaskActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });

        quoteView = findViewById(R.id.quoteView);

        //QUEUE = Volley.newRequestQueue(TaskActivity.this);
        //httpGET(URLHTTP, bitmap);
        new getQuoteTask().execute();
    }

/*
    /**
     * Convert an image to a byte array, upload to the Microsoft Cognitive Services API,
     * and return a result.
     *
     * @param currentBitmap the bitmap to process
     * @return unused result
     *

     public void httpGET(String url, Bitmap currentBitmap) {
         /*
          * Convert the image from a Bitmap to a byte array for upload.
          *

         final ByteArrayOutputStream stream = new ByteArrayOutputStream();
         currentBitmap.compress(Bitmap.CompressFormat.PNG,
                 100, stream);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    parseJson(response);
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
*/

    // a method that display a quote each time a photo is taken by the user.
        class getQuoteTask extends AsyncTask<Void,Void,String> {
        private Exception exception;

        @Override
        protected String doInBackground(Void... voids) {
            try {
                Forismatic.Quote quote = new Forismatic(Forismatic.ENGLISH).getQuote();
                String text = quote.getQuoteText();
                System.out.println(text);
                return text;
            } catch (Exception e) {
                this.exception = e;
                System.out.println(e);
                return "Start exploring!";
            }
        }
        protected void onPostExecute(String result) {
            quoteText = result + "\n" + "Remaining task:" + numOfTask;
            quoteView.setText(quoteText);
            numOfTask--;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent list) {
        super.onActivityResult(requestCode, resultCode, list);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    bmp1 = (Bitmap) extras.get("data");
                    imageBToL.setImageBitmap(bmp1);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    bmp2 = (Bitmap) extras.get("data");
                    imageBToR.setImageBitmap(bmp2);
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    bmp3 = (Bitmap) extras.get("data");
                    imageBDoL.setImageBitmap(bmp3);
                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    bmp4 = (Bitmap) extras.get("data");
                    imageBDoR.setImageBitmap(bmp4);
                }
                break;
        }
    }
}
