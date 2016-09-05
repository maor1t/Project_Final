package com.example.maor1t.project_final;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;



public class ListFrag extends Fragment implements LocationListener {
    FrameLayout containerphone;
    FrameLayout   containerlist;
    ArrayList<place> arrayList;
    AdapterArray adapter;
    ListView LV;
    String name;
    double lat;
    double lng;
    View v;
    LatLon latinter;
    LatLon lnginter;
    EditText et;
    private LocationManager locationManager;
    private String locationProvider;
    double latmyself;
    double lngmyself;
    Location location;
    SharedPreferences prefs;

    SharedPreferences.Editor editor;
    @Override
    public void onAttach(Activity activity) {

        try {
            latinter = (LatLon) activity;
            lnginter = (LatLon) activity;

        } catch (ClassCastException e) {
            Log.d("err", "Yor activity must implement ChangeCarListener for the click to work!! ");
        }

        super.onAttach(activity);
    }

    public ListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        v = inflater.inflate(R.layout.fragment_list, container, false);

        if(savedInstanceState!=null){
            latmyself = savedInstanceState.getDouble("latmyself");
            lngmyself = savedInstanceState.getDouble("lngmyself");
        }
        LV = (ListView) v.findViewById(R.id.listView5);

        et = (EditText) v.findViewById(R.id.listET);

         containerphone = (FrameLayout)v.findViewById(R.id.container);
           containerlist = (FrameLayout)v.findViewById(R.id.containerlist);


        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
         editor = prefs.edit();


        if(savedInstanceState!=null){
            latmyself = savedInstanceState.getDouble("latmyself");
            lngmyself = savedInstanceState.getDouble("lngmyself");

        }

        Button btnsearch = (Button) v.findViewById(R.id.listbtn);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("searchs")
                        .setMessage("how do you want to search by?")
                        .setPositiveButton("GPSNearBy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                if (location == null) {
                                    Toast.makeText(getActivity(), "No GPS Detected", Toast.LENGTH_SHORT).show();
                                } else {
                                    String place = et.getText().toString();
                                    Intent intent = new Intent(getActivity(), SearchNearByService.class);
                                    intent.putExtra("placenearby", place);
                                    intent.putExtra("lat", location.getLatitude());
                                    intent.putExtra("lng", location.getLongitude());
                                    getActivity().startService(intent);

                                }

                            }
                        })
                        .setNegativeButton("search text places", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String place = et.getText().toString();
                                Intent intent = new Intent(getActivity(), SearchPlace.class);
                                intent.putExtra("place", place);
                                getActivity().startService(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });
        IntentFilter filter = new IntentFilter("broadcast.local.com.localbroadcast.FINISHEEEEED");
        FinishedReciever reciever = new FinishedReciever();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(reciever, filter);


        IntentFilter filter1 = new IntentFilter("broadcast.local.com.localbroadcast.maor");
        FinishedReciever2 reciever1 = new FinishedReciever2();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(reciever1, filter1);


        locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_LOW);
        locationProvider = locationManager.getBestProvider(criteria, true);


    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

    }
        try{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 1, this);

            latmyself = location.getLatitude();
            lngmyself = location.getLongitude();

        }catch (Exception e){

        }









    Button btnfavorite = (Button)v.findViewById(R.id.favoriteslist);
        btnfavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                btnfavorites();

            }


        });

        Button btnhistory = (Button)v.findViewById(R.id.historylist);
        btnhistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                btnhistory();

            }


        });



        Button btndelete = (Button) v.findViewById(R.id.deletebtn);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler hand = new DBHandler(getActivity());
                hand.deleteplaces();


                Intent broadcastMessage = new Intent("broadcast.local.com.localbroadcast.FINISHEEEEED");

                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(broadcastMessage);
            }
        });

        DBHandler handler = new DBHandler(getActivity());

        arrayList = new ArrayList<>();

        arrayList = handler.getPlaces();



        adapter = new AdapterArray(getActivity(), R.layout.adapterlist, arrayList);
        LV.setAdapter(adapter);


        registerForContextMenu(LV);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    place m = arrayList.get(position);
                    double lat = m.getLat();
                    double lng = m.getLng();
                    String name = m.getName();


                    double latmyself=0;
                    double lngmyself=0;
                    try{
                     latmyself= location.getLatitude();
                     lngmyself = location.getLongitude();
                }catch (Exception e){
                    Log.d("","");
                }
                     latinter.getlatlng( lat , lng ,name , latmyself , lngmyself);


            }

        });


            return v;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.favorite, menu);
        registerForContextMenu(v.findViewById(R.id.listView5));


            }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.shareintent) {

            AdapterView.AdapterContextMenuInfo info1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            place m2 = arrayList.get(info1.position);
            String name2 = m2.getName();
            String address2 = m2.getAddress();


            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, name2 + " , " + address2);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.app_name)));

        }else{
            Toast.makeText(getActivity(),"added to favorites",Toast.LENGTH_SHORT).show();

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            DBHandler hand = new DBHandler(getActivity());

            place m = arrayList.get(info.position);
            String name = m.getName();
            String address = m.getAddress();
            double lat = m.getLat();
            double lng = m.getLng();
            String photo = m.getPhoto();

            place place1 = new place( name , address , lat ,lng , photo);
            hand.addFavorite(place1);


                return super.onOptionsItemSelected(item);
        }

        adapter.notifyDataSetChanged();

        return super.onContextItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location=location;


        double lat = this.location.getLatitude();
        double lng = this.location.getLongitude();


        editor.putFloat("lat",(float)lat);
        editor.putFloat("lng",(float)lng);


        editor.commit();



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }
    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    class FinishedReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            DBHandler handler = new DBHandler(context);
            arrayList = handler.getPlaces();
            adapter = new AdapterArray(context, R.layout.adapterlist, arrayList);
            LV.setAdapter(adapter);


        }


    }

    class FinishedReciever2 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(getActivity(), "no internet", Toast.LENGTH_SHORT).show();

        }


    }


    public void btnfavorites(){

        Intent intent = new Intent(getActivity(),Favorite.class);
        getActivity().startActivity(intent);

    }


    public void btnhistory() {

        Intent intent = new Intent(getActivity(), History.class);
        getActivity().startActivity(intent);


       }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putDouble("latmyself", latmyself);
        savedInstanceState.putDouble("lngmyself", lngmyself);

        super.onSaveInstanceState(savedInstanceState);
    }
           }