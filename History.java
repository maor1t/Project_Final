package com.example.maor1t.project_final;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {


    String name;
    double lat;
    double lng;
    ListView lv;
    AdapterArray adapter;
    ArrayList<place> array ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button btndelete = (Button) findViewById(R.id.historydelete);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler hand = new DBHandler(History.this);
                hand.deletehistory();


                array = hand.getHistory();
                adapter = new AdapterArray(History.this, R.layout.adapterlist, array);
                lv.setAdapter(adapter);
            }
        });


        lv= (ListView)findViewById(R.id.listViewhis);
        array = new ArrayList<>();
        DBHandler hand = new DBHandler(History.this);
        array = hand.getHistory();
        adapter = new AdapterArray(History.this, R.layout.adapterlist, array);
        lv.setAdapter(adapter);



    }



}