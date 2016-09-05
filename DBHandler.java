package com.example.maor1t.project_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;




public class DBHandler {


    private static Context context;

    ArrayList<place> array = new ArrayList<place>();


    public DBHandler(Context context) {
        this.context = context;
    }


    public static void addPlace(place place) {
        DBHelper helper = new DBHelper(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Places.NAME, place.getName());
        contentValues.put(Contract.Places.ADDRESS, place.getAddress());
        contentValues.put(Contract.Places.DISTANCE, place.getDistance());
        contentValues.put(Contract.Places.PHOTO, place.getPhoto());
        contentValues.put(Contract.Places.LOCATION, place.getLocation());
        contentValues.put(Contract.Places.LAT, place.getLat());
        contentValues.put(Contract.Places.LNG, place.getLng());

        helper.getWritableDatabase().insert(Contract.Places.TABLE_NAME, null, contentValues);


    }//*************************************************************************


    public static void addFavorite(place place) {
        DBHelper helper = new DBHelper(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Favorites.NAME, place.getName());
        contentValues.put(Contract.Favorites.ADDRESS, place.getAddress());
        contentValues.put(Contract.Favorites.LAT, place.getLat());
        contentValues.put(Contract.Favorites.LNG, place.getLng());
        contentValues.put(Contract.Favorites.PHOTO, place.getPhoto());

        helper.getWritableDatabase().insert(Contract.Favorites.TABLE_NAME, null, contentValues);


    }

     //*************************************************************************


    public static void addHistory(place place) {

        DBHelper helper = new DBHelper(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.History.NAME, place.getName());
        contentValues.put(Contract.History.ADDRESS, place.getAddress());
        contentValues.put(Contract.History.LAT, place.getLat());
        contentValues.put(Contract.History.LNG, place.getLng());
        contentValues.put(Contract.History.PHOTO, place.getPhoto());

        helper.getWritableDatabase().insert(Contract.History.TABLE_NAME, null, contentValues);


    }

//*************************************************************************
    public ArrayList<place> getPlaces() {
        DBHelper helper;
        if(context != null)
       helper = new DBHelper(context);
        else
        {
             helper = new DBHelper(Contract.Places.context);
        }

        Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM " + Contract.Places.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(Contract.Places.NAME));
            String address = cursor.getString(cursor.getColumnIndex(Contract.Places.ADDRESS));
            double lat = cursor.getDouble(cursor.getColumnIndex(Contract.Places.LAT));
            double lng = cursor.getDouble(cursor.getColumnIndex(Contract.Places.LNG));
            String photo =cursor.getString(cursor.getColumnIndex(Contract.Places.PHOTO));


            place m = new place(name, address, lat, lng , photo );
            array.add(m);
        }
        return array;
    }

//*********************************************************************

    public ArrayList<place> getFavorite() {
        DBHelper helper;
        if(context != null)
            helper = new DBHelper(context);
        else
        {
            helper = new DBHelper(Contract.Places.context);
        }

        Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM " + Contract.Favorites.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(Contract.Favorites.NAME));
            String address = cursor.getString(cursor.getColumnIndex(Contract.Favorites.ADDRESS));
            String photo = cursor.getString(cursor.getColumnIndex(Contract.Favorites.PHOTO));
            double lat = cursor.getDouble(cursor.getColumnIndex(Contract.Favorites.LAT));
            double lng = cursor.getDouble(cursor.getColumnIndex(Contract.Favorites.LNG));


            place m = new place(name, address , lat, lng ,photo);
            array.add(m);
        }

        return array;
    }
//*********************************************************************


        public ArrayList<place> getHistory() {

DBHelper helper;
            if(context != null)
                       helper= new DBHelper(context);
            else
            {
                helper = new DBHelper(Contract.History.context);
            }


            Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM " + Contract.History.TABLE_NAME, null);

            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(Contract.History.NAME));
                String address = cursor.getString(cursor.getColumnIndex(Contract.History.ADDRESS));
                String photo = cursor.getString(cursor.getColumnIndex(Contract.History.PHOTO));
                double lat = cursor.getDouble(cursor.getColumnIndex(Contract.History.LAT));
                double lng = cursor.getDouble(cursor.getColumnIndex(Contract.History.LNG));


                place m = new place(name, address , lat , lng  , photo);
                array.add(m);
            }

            return array;
        }


        //******************************************************************


    public void deleteplaces() {
        DBHelper helper = new DBHelper(context);
        helper.getWritableDatabase().delete(Contract.Places.TABLE_NAME, null, null);
    }

    public void deletefavorites() {
        DBHelper helper = new DBHelper(context);
        helper.getWritableDatabase().delete(Contract.Favorites.TABLE_NAME, null, null);
    }

    public void deletehistory() {
        DBHelper helper = new DBHelper(context);
        helper.getWritableDatabase().delete(Contract.History.TABLE_NAME, null, null);
    }


}