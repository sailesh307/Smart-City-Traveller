package com.example.smartcitytraveller;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Venue implements Serializable {

    private transient JSONObject place;

    private String name;
    private String address;
    private double latitude;
    private double longitude;

    /**
     * Constructor that take JSONObject place and get data from it
     * @param place
     */
    Venue(JSONObject place){
        this.place = place;
        setName();
        setAddress();
        setLatitude();
        setLongitude();
    }

    /**
     * Setter Functions
     */
    private void setName(){
        try {
            name = place.getString("name");
        } catch (JSONException e) {
            name = " ";
            e.printStackTrace();
        }
    }

    private void setLatitude(){
        try {
            latitude = place.getJSONObject("geocodes").getJSONObject("main").getDouble("latitude");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setLongitude(){
        try {
            longitude = place.getJSONObject("geocodes").getJSONObject("main").getDouble("longitude");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setAddress() {
        String address = "";

        try {
            JSONObject location = place.getJSONObject("location");
            //////////////Local address//////////////////
            try {
                address += location.getString("address") + " ";
            } catch (Exception e){ e.printStackTrace(); }
            ///////////////// city ////////////////////
            try{
                address += location.getString("locality") + " ";
            } catch (Exception e) { e.printStackTrace(); }
            ///////////////// postal Code ///////////////////
            try{
                address += location.getString("postcode");
            } catch (Exception e) { e.printStackTrace(); }
        } catch (Exception e){
            e.printStackTrace();
        }
        this.address = address; // finally setting address
    }

    /**
     * getter functions
     */
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
