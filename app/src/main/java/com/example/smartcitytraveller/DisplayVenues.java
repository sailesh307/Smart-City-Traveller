package com.example.smartcitytraveller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayVenues extends AppCompatActivity {

    //
        ArrayList<Venue> venueArrayList;
    //
    private RecyclerView venues;
    private ImageView mapIcon;
    private String city, venueType;

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

        String domain = "https://api.foursquare.com/v3/places/search?&near=";
        String url = domain + city
                + "&query=" + venueType
                + "&limit=10";
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
                        Log.d("myurl", response.toString());
                        progressDialog.hide();

                        ParseData parseData = new ParseData(response);
                        venueArrayList = parseData.getVenueList();

                        CustomAdapterForEachPlace adapter = new CustomAdapterForEachPlace(venueArrayList);
                        venues.setLayoutManager(new LinearLayoutManager(DisplayVenues.this));
                        venues.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        /* stop Progress Bar */
                        progressDialog.hide();
                        ////////// Show Alert Dialog //////////
                        AlertDialog alertDialog = new AlertDialog.Builder(DisplayVenues.this)
                                .setTitle("Alert")
                                .setTitle("Unexpected error occurred !!!")
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ///////// close this activity //////////
                                        finish();
                                    }
                                })
                                .show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", getResources().getString(R.string.AUTH_ID));
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }
}