package com.example.smartcitytraveller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseData {

    /**
     * ArrayList to store parsed data of all venues
     */

    private ArrayList<Venue> venueList = new ArrayList<Venue>();

    /**
     * Constructor to Parse the initial data and then store it into ArrayList
     * @param data
     */

    ParseData(JSONObject data){
        try{
            JSONArray venueArray = data.getJSONArray("results");

            for(int i = 0; i < venueArray.length(); i++){
                JSONObject eachVenue = venueArray.getJSONObject(i);
                Venue venue = new Venue(eachVenue);
                venueList.add(venue);
            }
        }
        catch (JSONException jsonException){
            jsonException.printStackTrace();
        }
    }

    public ArrayList<Venue> getVenueList(){
        return venueList;
    }

}
