package com.example.a125finalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
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

    private ImageButton imageBToL;
    private ImageButton imageBToR;
    private ImageButton imageBDoL;
    private ImageButton imageBDoR;
    private Button buttonFinish;
    private Intent intentI;
    private Intent intentII;
    private Intent intentIII;
    private Intent intentIV;
    private Bitmap bmp1;
    private Bitmap bmp2;
    private Bitmap bmp3;
    private Bitmap bmp4;

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
                try {
                    //Write file
                    String filename1 = "bitmap1.png";
                    FileOutputStream stream1 = openFileOutput(filename1, Context.MODE_PRIVATE);
                    bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
                    String filename2 = "bitmap2.png";
                    FileOutputStream stream2 = openFileOutput(filename2, Context.MODE_PRIVATE);
                    bmp2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                    String filename3 = "bitmap3.png";
                    FileOutputStream stream3 = openFileOutput(filename3, Context.MODE_PRIVATE);
                    bmp3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
                    String filename4 = "bitmap4.png";
                    FileOutputStream stream4 = openFileOutput(filename4, Context.MODE_PRIVATE);
                    bmp4.compress(Bitmap.CompressFormat.PNG, 100, stream4);

                    //Cleanup
                    stream1.close();
                    bmp1.recycle();
                    stream2.close();
                    bmp2.recycle();
                    stream3.close();
                    bmp3.recycle();
                    stream4.close();
                    bmp4.recycle();

                    //Pop intent
                    Intent in1 = new Intent(TaskActivity.this, AlbumActivity.class);
                    in1.putExtra("image1", filename1);
                    in1.putExtra("image2", filename2);
                    in1.putExtra("image3", filename3);
                    in1.putExtra("image4", filename4);
                    startActivity(in1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent list) {
        super.onActivityResult(requestCode, resultCode, list);
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Bundle extras = list.getExtras();
                    bmp1 = (Bitmap) extras.get("data");
                    imageBToL.setImageBitmap(bmp1);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    bmp2 = (Bitmap) extras.get("data");
                    imageBToR.setImageBitmap(bmp2);
                }
                break;
            case 3:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    bmp3 = (Bitmap) extras.get("data");
                    imageBDoL.setImageBitmap(bmp3);
                }
                break;
            case 4:
                if(resultCode == RESULT_OK) {
                    Bundle extras = list.getExtras();
                    bmp4 = (Bitmap) extras.get("data");
                    imageBDoR.setImageBitmap(bmp4);
                }
                break;
        }
    }
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
