package SupportFeatures;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a125finalproject.R;
import com.example.a125finalproject.TaskActivity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AzureNew {
    /*
    public static final String TAG = "MYTAG";
    RequestQueue QUEUE;
    String URLHTTP, APIkey;

    //supposed to put in onCreate
    public void main(String []args) {
        QUEUE = Volley.newRequestQueue(TaskActivity.this);
        URLHTTP = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze";
        APIkey = "6bf2823f8f9940ad95a84f981a9f8bab";
        httpGET(URLHTTP);

    }
    public void httpGET(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
        };
        QUEUE.add(stringRequest);
    }
    */

    // // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
/*
    public static void main(String[] args)
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/vision/v1.0/analyze");

            builder.setParameter("visualFeatures", "Categories");
            builder.setParameter("details", "{string}");
            builder.setParameter("language", "en");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    */
}
