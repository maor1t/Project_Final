package com.example.maor1t.project_final;

/**
 * Created by maor1t on 03/08/2016.
 */
public class place {

    private String name;
    private String address;
    private String location;
    private double distance;
    private String photo;
    private double lat;
    private double lng;




    public place(String name, String address , double lat , double lng , String photo ) {


        this.photo=photo;
        this.name =name;
        this.address =address;
        this.lat =lat;
        this.lng =lng;
    }



    public place(String name , String address  , double lat , double lng) {
        this.lat=lat;
        this.lng=lng;
        this.name =name;
        this.address =address;
    }




    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public place(String name, String address, String location, double distance, String photo , double lat , double lng) {

        this.name = name;
        this.address = address;
        this.location = location;
        this.distance = distance;
        this.photo = photo;
        this.lat =lat;
        this.lng =lng;
    }

    @Override
    public String toString() {
        return name + address + photo + distance+" ";
    }

//
}
