package com.example.maor1t.project_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by maor1t on 07/08/2016.
 */
public class AdapterArray extends ArrayAdapter<place> {
    double result;
    View currentViewItem;
    double latmyself;
    double lngmyself;
    double lat;
    double lng;
    public AdapterArray(Context context, int resource, List<place> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        latmyself = (double) prefs.getFloat("lat", 0);
        lngmyself = (double) prefs.getFloat("lng", 0);

        currentViewItem = convertView;


        if (currentViewItem == null) {
            LayoutInflater layoutinflater = LayoutInflater.from(getContext());
            currentViewItem = layoutinflater.inflate(R.layout.adapterlist, null);
        }
        place place = (place) getItem(position);

        String name = place.getName();
        String address = place.getAddress();
        lat = place.getLat();
        lng = place.getLng();


        TextView kmTV = (TextView) currentViewItem.findViewById(R.id.kmTV);
        TextView nameTV = (TextView) currentViewItem.findViewById(R.id.name);
        TextView addressTV = (TextView) currentViewItem.findViewById(R.id.address);


        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

if(latmyself!=0.0&&lngmyself!=0.0){
    result  =  haversine(latmyself , lngmyself, lat, lng);

}

        boolean ShowKM=prefs.getBoolean("km",true);
        if(ShowKM)
        {
            result=Math.floor(result*100)/100;
            kmTV.setText(result+" Km");

        }

        else {
            double ml=result / 1.61;
            ml = Math.floor(ml * 100) / 100;
            kmTV.setText(ml+" Mile");

        }

        nameTV.setText(name);
        addressTV.setText(address);

        String photo = place.getPhoto();
        ImageView imageview = (ImageView) currentViewItem.findViewById(R.id.imgadapter);
        if (photo != null) {
            if (!photo.equals("")) {
                Picasso.with(getContext()).load(photo).into(imageview);
            }

        }


        {


            return currentViewItem;
        }

    }


    public double haversine(double lat1, double lng1, double lat2, double lng2) {
        int r = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c;
        return d;
    }

}