package com.example.maor1t.project_final;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.BatteryManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LatLon {

    Cursor savedInstanceState;
    FrameLayout containerlist;
    FrameLayout container;
    PowerConnectionReceiver receiver;


    double lngmyself;
    double latmyself;
    String name;
    double lat;
    double lng;


    ListFrag list;
    FragmentMap map;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        receiver = new PowerConnectionReceiver();

        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver, ifilter);


        Contract.Places.context = this;

        IntentFilter filter = new IntentFilter("broadcast.local.com.localbroadcast.maorr");
        FinishedReciever reciever = new FinishedReciever();
        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, filter);


        if (savedInstanceState == null) {
            list = new ListFrag();

            containerlist = (FrameLayout) findViewById(R.id.containerlist);
            container = (FrameLayout) findViewById(R.id.container);


            if (containerlist != null) {

                ListFrag list = new ListFrag();

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.add(R.id.containerlist, list).commit();
            }
            if (container != null) {

                ListFrag list = new ListFrag();

                manager = getFragmentManager();
                transaction = manager.beginTransaction();
                transaction.add(R.id.container, list).commit();
            }


        }
    }





      @Override
            public void getlatlng(double lat , double lng , String name , double myselflat , double myselflng) {


                 container = (FrameLayout)findViewById(R.id.container) ;
                 containerlist = (FrameLayout)findViewById(R.id.containerlist) ;


                map = new FragmentMap();

      if(container!=null){

          FragmentManager manager = getFragmentManager();
          transaction = manager.beginTransaction();
          transaction.addToBackStack("map");
          transaction.replace(R.id.container, map).commit();
      }


          if(containerlist!=null){
              FragmentManager manager2 = getFragmentManager();
              transaction = manager2.beginTransaction();
              transaction.addToBackStack("map");
              transaction.replace(R.id.containermap, map).commit();
          }


               map.latmyself= myselflat;
               map.lngmyself= myselflng;
               map.name = name;
               map.lat = lat;
               map.lng = lng;



            }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.settings);
        Button btn =(Button)item.getActionView();
        return true;
    }


    @Override
    protected void onResume() {

        Intent broadcastMessage = new Intent("broadcast.local.com.localbroadcast.FINISHEEEEED");

        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastMessage);
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:

                Intent intent = new Intent(this, PrefscreenActivity.class);
                startActivity(intent);

        }
        return true;
    }



    @Override
    public void onBackPressed() {

        FragmentManager manager = getFragmentManager();


        if(manager.getBackStackEntryCount() >0)
        {
            manager.popBackStack();
        }
        else
        {
            super.onBackPressed();
        }


    }


    FragmentManager manager = getFragmentManager();

public void backtolist(){
    containerlist = (FrameLayout)findViewById(R.id.containermap) ;
    container = (FrameLayout)findViewById(R.id.container) ;

    ListFrag list = new ListFrag();



    if(container!=null){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, list).commit();
    }


}


    class FinishedReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

         Toast.makeText(MainActivity.this, "no internet", Toast.LENGTH_SHORT).show();

        }
    }


    public class PowerConnectionReceiver extends BroadcastReceiver {

        public PowerConnectionReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                Toast.makeText(context, " charging", Toast.LENGTH_SHORT).show();
            } else {
                intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED);
                Toast.makeText(context, "not charging", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }




        }

