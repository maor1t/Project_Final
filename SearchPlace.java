package com.example.maor1t.project_final;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;





public class SearchPlace extends IntentService {
   public String  locationProvider;
   Location location;

    public SearchPlace() {
        super("SearchPlace");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String getext = intent.getStringExtra("place");



        getext = getext.replace(" ", "+");
        String responseStr = null;
        String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="
                + getext + "/&key=AIzaSyBc21yafHHscs4Zhy3Z4hnsHEYxFnPeJI4";

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet(URL);
            HttpResponse httpResponse = httpClient.execute(get);
            HttpEntity httpEntity = httpResponse.getEntity();
            responseStr = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {

        } catch (ClientProtocolException e) {

        } catch (IOException e) {
            e.getMessage();
        }



        if(responseStr!=null){

            try {
                JSONObject mainObject = new JSONObject(responseStr);
                JSONArray array = mainObject.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject curentObject = array.getJSONObject(i);


                    String name = curentObject.getString("name");
                    String address = curentObject.getString("formatted_address");
                    JSONObject geometryObject = curentObject.getJSONObject("geometry");
                    JSONObject locationObject = geometryObject.getJSONObject("location");
                    double lat = locationObject.getDouble("lat");
                    double lng = locationObject.getDouble("lng");

                    String photoURL = "";
                    try {
                        JSONArray photosArray = curentObject.getJSONArray("photos");
                        JSONObject photo1 = photosArray.getJSONObject(0);
                        photoURL = photo1.getString("photo_reference");
                        photoURL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=100&photoreference="
                                + photoURL + "&key=AIzaSyAp8shKGux7DPt2dBJGZlUGFuJigvfHOpk";



                        Log.d("debug", "json info: name:"+name+" lat:  "+lat+" lng: "+lng+  "photo:  "+photoURL);

                        place place = new place(name,address,lat ,lng , photoURL  );
                        place placeHistory = new place(name,address,lat, lng , photoURL);

                        DBHandler handler = new DBHandler(this);

                        handler.addPlace(place);
                        handler.addHistory(placeHistory);


                    } catch (JSONException ex) {
                        Log.e("error", ex.getMessage());


                    }
                }


                Intent broadcastMessage = new Intent("broadcast.local.com.localbroadcast.FINISHEEEEED");
                LocalBroadcastManager.getInstance(this).sendBroadcast( broadcastMessage);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else
        {
            Intent broadcastMessage = new Intent("broadcast.local.com.localbroadcast.maorr");
            LocalBroadcastManager.getInstance(this).sendBroadcast( broadcastMessage);


        }

    }



}
