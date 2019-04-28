package SupportFeatures;

import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// HttpClient libraries

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Azure {
    static String endpoint = "https://api.cognitive.microsoft.com/bing/v7.0/images/visualsearch";
    static String subscriptionKey = "6bf2823f8f9940ad95a84f981a9f8bab";
    static String imagePath = "https://memorynotfound.com/wp-content/uploads/java-duke.png";
    public static void main(String []args) throws Exception {
        System.out.println("Hello World!");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .addBinaryBody("image", new File(imagePath))
                .build();
        HttpPost httpPost = new HttpPost(endpoint);
        httpPost.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
        httpPost.setEntity(entity);
        try {
            HttpResponse response = httpClient.execute(httpPost);
            InputStream stream = response.getEntity().getContent();
        } catch (Exception e) {
            System.out.println(e);
        }
        HttpResponse response = httpClient.execute(httpPost);
        InputStream stream = response.getEntity().getContent();
        String json = new Scanner(stream).useDelimiter("\\A").next();
        System.out.println("\nJSON Response:\n");
        System.out.println(prettify(json));
    }
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
}
