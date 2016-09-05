package com.example.maor1t.project_final;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class FragmentMap extends Fragment implements OnMapReadyCallback {

    public double lat;
    public double lng;
    public String name;

    double latmyself;
    double lngmyself;
    View v;
    MapFragment map = new MapFragment();
    TextView tv;

    FrameLayout containerphone;
    FrameLayout containerlist;


    public FragmentMap() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_map, container, false);
        containerphone = (FrameLayout)v.findViewById(R.id.container);
        containerlist = (FrameLayout)v.findViewById(R.id.containerlist);

        if(savedInstanceState!=null){
            latmyself = savedInstanceState.getDouble("latmyself");
            lngmyself = savedInstanceState.getDouble("lngmyself");
            lat = savedInstanceState.getDouble("lat");
            lng = savedInstanceState.getDouble("lng");
            name =  savedInstanceState.getString("name");
        }



if(containerphone!=null){}


        if(containerlist!=null){
            FragmentMap fragmentMap = new FragmentMap();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction   transaction2 = manager.beginTransaction();
            transaction2.add(R.id.containermap, fragmentMap).commit();
        }




        FragmentManager manager = getFragmentManager();
    FragmentTransaction   transaction2 = manager.beginTransaction();
    transaction2.add(R.id.flmap, map).commit();
    map.getMapAsync(this);




        return v;

    }

    @Override
    public void onMapReady(GoogleMap map) {


        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        LatLng position = new LatLng(lat,lng);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(position, 18);
        map.moveCamera(update);


        MarkerOptions markerOptions = new MarkerOptions()
                .position(position)
                .title(name)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        Marker marker = map.addMarker(markerOptions);



try {
if(latmyself!=0&&lngmyself!=0){
    LatLng position2 = new LatLng(latmyself,lngmyself);

    MarkerOptions markerOptionsmyself = new MarkerOptions()
            .position(position2)
            .title("my home")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
    Marker marker2 = map.addMarker(markerOptionsmyself);

}else{
    Toast.makeText(getActivity(),"Your GPS Still Not Detected yet",Toast.LENGTH_SHORT).show();
}
    Polyline line = map.addPolyline(new PolylineOptions()
            .add(new LatLng(latmyself,lngmyself),new LatLng(lat,lng))
            .width(5)
            .color(Color.BLUE));
}
catch (Exception e) {
}

        }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putDouble("latmyself", latmyself);
        savedInstanceState.putDouble("lngmyself", lngmyself);
        savedInstanceState.putDouble("lat", lat);
        savedInstanceState.putDouble("lng", lng);
        savedInstanceState.putString("name", name);

        super.onSaveInstanceState(savedInstanceState);
    }
}