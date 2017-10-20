package com.epicodus.muse.services;

import android.util.Log;

import com.epicodus.muse.Constants;
import com.epicodus.muse.models.Artifact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by momma on 10/19/17.
 */
//    https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.collection&access_token=c37cbfe0d837df6dc706875195730201&color=daa520&page=1&per_page=10'

    //https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.collection&access_token=f93f6f24f4b39e1292abae903054adb6&color=%2300ffff
    //https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.objects&access_token=c37cbfe0d837df6dc706875195730201&color=lightpink&page=1&per_page=10
    //https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.objects&access_token=c37cbfe0d837df6dc706875195730201&color=lightpink&has_images=true&page=1&per_page=3

//    b: 1024px on the longest side.
//            z: 640px on the longest side.
//            n: 320px on the longest side.
//            d: 320px on the longest side. This image is also dithered using our Atkinson dithering server for smaller filesize.
//            sq: 300px square image that is cropped from the b image.


public class CooperHewittService {
    public static final String TAG = CooperHewittService.class.getSimpleName();

    public static void callArtifacts(String color, Callback callback) {

        Log.v(TAG, ">>>Color selected  "+ color);
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.COOPERHEWITT_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.COOPERHEWITT_TOKEN);
        urlBuilder.addQueryParameter(Constants.COOPERHEWITT_COLOR_QUERY_PARAMETER, color);

        String url = urlBuilder.build().toString();
        Log.v(TAG, ">>>String url  "+ url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        okhttp3.Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Artifact> processResults(Response response) {
        Log.d(TAG, ">>>processing results" );

        ArrayList<Artifact> artifacts = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject cooperhewittJSON = new JSONObject(jsonData);
            JSONArray objectsJSON = cooperhewittJSON.getJSONArray("objects");
            for (int i = 0; i < objectsJSON.length(); i++) {
                JSONObject obj = objectsJSON.getJSONObject(i);
                String title = obj.getString("title");
                String type = obj.getString("type");
                String medium = obj.getString("medium");
                String date = obj.getString("date");
                String description = obj.getString("description");
                String justification = obj.getString("justification");
                String objectId = obj.getString("id");

//                JSONArray imagesJSON = obj.getJSONArray("images");
//                ArrayList<String> images = new ArrayList<>();
//
//                //first index is 'b' image
//                String imageUrl = imagesJSON.getJSONObject(0).getString("url");
//                Log.v(TAG, ">>>Image URL  "+ imageUrl);

                String imageUrl = "just keep swimming";
                Artifact artifact = new Artifact(title, type, medium, date, description, justification, objectId, imageUrl);
                artifacts.add(artifact);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return artifacts;
    }


}
