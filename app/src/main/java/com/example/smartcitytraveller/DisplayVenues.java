package com.example.smartcitytraveller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DisplayVenues extends AppCompatActivity {

    //
        ArrayList<Venue> venueArrayList;
    //
    private RecyclerView venues;
    private ImageView mapIcon;
    private String city, venueType;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_venues);

        TextView tvVenueType = findViewById(R.id.venueType);
        venues = findViewById(R.id.rvVenueList);
        mapIcon = findViewById(R.id.mapIcon);

        // getting intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            venueType = (String) bundle.get("VENUE_TYPE");
            city = (String) bundle.get("CITY_NAME");
            position = bundle.getInt("POSITION");
            tvVenueType.setText(city.toUpperCase() + " " + venueType.toUpperCase());
        }
        setVenues();


        /////////// on click listener for map icon /////////
        mapIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayVenues.this, Location.class);
                //passing venue list
                    intent.putExtra("VENUE_LIST", venueArrayList);
                //
                startActivity(intent);
            }
        });

    }

    /**
     * @return String (url of venue selected by user)
     */
    String getUrl(){
        String CLIENT_ID = getResources().getString(R.string.CLIENT_ID);
        String CLIENT_SECRET = getResources().getString(R.string.CLIENT_SECRET);
        String domain = "https://api.foursquare.com/v2/venues/search?near=";
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date()); // for getting present date

        String url = domain + city
                + "&client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET
                + "&v=" + date
                + "&limit=10"
                + "&query=" + venueType;

        Log.d("url", url);

        return url;
    }

    void setVenues(){

        //////////////// Progress Bar ////////////////
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Venues...");
        progressDialog.show();
        //////////////// Progress Bar started/////////////////

        String url = getUrl();
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        /* stop Progress Bar */
                        progressDialog.hide();
                        Toast.makeText(DisplayVenues.this, "this is message", Toast.LENGTH_SHORT).show();

                        ParseData parseData = new ParseData(response);
                        venueArrayList = parseData.getVenueList();
                        //just testing
//                        longitude = venueArrayList.get(0).getLongitude();

                        CustomAdapterForEachPlace adapter = new CustomAdapterForEachPlace(venueArrayList);
                        venues.setLayoutManager(new LinearLayoutManager(DisplayVenues.this));
                        venues.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        /* stop Progress Bar */
//                        progressDialog.hide();
//                        longitude = 0.0;
                        Toast.makeText(DisplayVenues.this, "error.getMessage()", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }
}