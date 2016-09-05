package com.example.maor1t.project_final;

import android.content.Context;

/**
 * Created by maor1t on 03/08/2016.
 */
public class Contract {



    public static class Places {
        public static Context context;

        public static String TABLE_NAME="places";
        public static String _ID="_id";
        public static String NAME="name";
        public static String ADDRESS="address";
        public static String DISTANCE="distance";
        public static String LOCATION="location";
        public static String PHOTO="photo";
        public static String LAT="lat";
        public static String LNG="lng";
    }



    public static class History {
        public static Context context;

        public  static String TABLE_NAME="history";
       public static String _ID="_id";
        public static String NAME="name";
        public static String ADDRESS="address";
       public static String DISTANCE="distance";
        public static String LOCATION="location";
       public static String PHOTO="photo";
        public static String LAT="lat";
        public static String LNG="lng";
    }

    public static class Favorites {
        public static Context context;
        public static String TABLE_NAME="favorites";
       public static String _ID="_id";
        public static String NAME="name";
        public static String ADDRESS="address";
        public static String DISTANCE="distance";
        public static String LOCATION="location";
        public static String PHOTO="photo";
        public static String LAT="lat";
        public static String LNG="lng";

    }
}
