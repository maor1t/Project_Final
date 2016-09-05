package com.example.maor1t.project_final;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;



public class Favorite extends AppCompatActivity {

    ListView lv;
    AdapterArray adapter;
    ArrayList<place> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);




        Button btndelete = (Button) findViewById(R.id.favoritedelete);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler hand = new DBHandler(Favorite.this);
                hand.deletefavorites();


                array = hand.getFavorite();
                adapter=new AdapterArray(Favorite.this,R.layout.adapterlist,array);
                lv.setAdapter(adapter);

            }
        });

        lv = (ListView) findViewById(R.id.listViewfav);
        array = new ArrayList<>();
        DBHandler hand = new DBHandler(this);
        array = hand.getFavorite();


        adapter = new AdapterArray(this, R.layout.adapterlist, array);
        lv.setAdapter(adapter);


    }





}