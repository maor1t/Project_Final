package com.example.maor1t.project_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maor1t on 03/08/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Places.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql =
                "CREATE TABLE " + Contract.Places.TABLE_NAME
                        + "("
                        + Contract.Places._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + Contract.Places.NAME + " TEXT ,"
                        + Contract.Places.ADDRESS + " TEXT ,"
                        + Contract.Places.DISTANCE + " REAL ,"
                        + Contract.Places.LAT + " TEXT ,"
                        + Contract.Places.LNG + " TEXT ,"
                        + Contract.Places.LOCATION + " TEXT ,"
                        + Contract.Places.PHOTO + " TEXT "
                        + ") ";
        db.execSQL(sql);

        String  sqlhis =
                "CREATE TABLE " + Contract.History.TABLE_NAME
                        + "("
                        + Contract.History._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + Contract.History.NAME + " TEXT ,"
                        + Contract.History.ADDRESS + " TEXT ,"
                        + Contract.History.DISTANCE + " REAL ,"
                        + Contract.History.LAT + " TEXT ,"
                        + Contract.History.LNG + " TEXT ,"
                        + Contract.History.LOCATION + " TEXT ,"
                        + Contract.History.PHOTO + " TEXT "
                        + ") ";
        db.execSQL(sqlhis);

        String sqlfav =  "CREATE TABLE " + Contract.Favorites.TABLE_NAME
                        + "("
                        + Contract.Favorites._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + Contract.Favorites.NAME + " TEXT ,"
                        + Contract.Favorites.ADDRESS + " TEXT ,"
                        + Contract.Favorites.DISTANCE + " REAL ,"
                        + Contract.Favorites.LAT + " TEXT ,"
                        + Contract.Favorites.LNG + " TEXT ,"
                        + Contract.Favorites.LOCATION + " TEXT ,"
                        + Contract.Favorites.PHOTO + " TEXT "
                        + ") ";


        db.execSQL(sqlfav);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
