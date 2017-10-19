package com.epicodus.muse.services;

import android.util.Log;

import com.epicodus.muse.Constants;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by momma on 10/19/17.
 */
//    https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.collection&access_token=c37cbfe0d837df6dc706875195730201&color=daa520&page=1&per_page=10'

    //https://api.collection.cooperhewitt.org/rest/?method=cooperhewitt.search.collection&access_token=f93f6f24f4b39e1292abae903054adb6&color=%2300ffff

public class CooperHewittService {
    public static final String TAG = CooperHewittService.class.getSimpleName();

    public static void callArtifacts(String color, Callback callback) {

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

//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(SyncStateContract.Constants.API_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(SyncStateContract.Constants.NAME_SEARCH_QUERY_PARAMETER, movieTitle);
//        urlBuilder.addQueryParameter(SyncStateContract.Constants.API_KEY_QUERY_PARAMETER, SyncStateContract.Constants.API_KEY);
//        String url = urlBuilder.build().toString();
//        Log.v("String url", url);
//        Request request= new Request.Builder()
//                .url(url)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(callback);

    }

//    public ArrayList<Artifacts> processResults(Response response) {
//        Log.d(TAG, ">>>processing results" );
//
//        ArrayList<Artifacts> artifacts = new ArrayList<>();
//        try {
//            String jsonData = response.body().string();
//            JSONObject yelpJSON = new JSONObject(jsonData);
//            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
//            for (int i = 0; i < businessesJSON.length(); i++) {
//                JSONObject artifact = businessesJSON.getJSONObject(i);
//                String name = artifact.getString("name");
//                String phone = artifact.optString("display_phone", "Phone not available");
//                String website = artifact.getString("url");
//                double rating = artifact.getDouble("rating");
//
//                String imageUrl = artifact.getString("image_url");
//
//                double latitude = (double) artifact.getJSONObject("coordinates").getDouble("latitude");
//
//                double longitude = (double) artifact.getJSONObject("coordinates").getDouble("longitude");
//
//                ArrayList<String> address = new ArrayList<>();
//                JSONArray addressJSON = artifact.getJSONObject("location")
//                        .getJSONArray("display_address");
//                for (int y = 0; y < addressJSON.length(); y++) {
//                    address.add(addressJSON.get(y).toString());
//                }
//
//                ArrayList<String> categories = new ArrayList<>();
//                JSONArray categoriesJSON = artifact.getJSONArray("categories");
//
//                for (int y = 0; y < categoriesJSON.length(); y++) {
//                    categories.add(categoriesJSON.getJSONObject(y).getString("title"));
//                }
//                Artifcact artifact = new Artifcact(name, phone, website, rating,
//                        imageUrl, address, latitude, longitude, categories);
//                artifacts.add(artifact);
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        catch (JSONException e){
//            e.printStackTrace();
//        }
//        return artifacts;
//    }


}
