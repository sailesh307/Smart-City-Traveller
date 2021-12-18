package com.example.smartcitytraveller;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Venue implements Serializable {

    private transient JSONObject place;

    private String name;
    private String phone;
    private String address;
    private String icon;
    private double latitude;
    private double longitude;
    private int distance;

    /**
     * Constructor that take JSONObject place and get data from it
     * @param place
     */
    Venue(JSONObject place){
        this.place = place;
        setName();
        setPhone();
        setAddress();
        setLatitude();
        setLongitude();
        setDistance();
        setIcon();
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

    private void setPhone(){
        try {
            phone = place.getJSONObject("contact").getString("phone");
        } catch (JSONException e) {
            phone = " ";
            e.printStackTrace();
        }
    }

    private void setLatitude(){
        try {
            latitude = place.getJSONObject("location").getDouble("lat");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setLongitude(){
        try {
            longitude = place.getJSONObject("location").getDouble("lng");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setDistance(){
        try {
            distance = place.getJSONObject("location").getInt("distance");
        } catch (JSONException e) {
            distance = 0;
            e.printStackTrace();
        }
    }

    private void setIcon(){
        try {
            icon = place.getJSONArray("categories").getJSONObject(0).getJSONObject("icon").getString("prefix") + 64 + ".png";
            Log.d("iconTest", icon);
        } catch (JSONException e) {
            icon = "";
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
            ///////////////// Street //////////////////
            try{
                address += location.getString("crossStreet") + " ";
            } catch (Exception e){ e.printStackTrace(); }
            ///////////////// city ////////////////////
            try{
                address += location.getString("city") + " ";
            } catch (Exception e) { e.printStackTrace(); }
            ///////////////// postal Code ///////////////////
            try{
                address += location.getString("postalCode");
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

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getIcon() {
        return icon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getDistance() {
        return distance;
    }
}
