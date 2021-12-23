package com.example.smartcitytraveller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChooseVenueType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_venue_type);

        // getting intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String cityName = (String) bundle.get("CITY_NAME");
        Toast.makeText(this, cityName, Toast.LENGTH_SHORT).show();

        //////////////  after extracting CITY_NAME /////////////////
        // show different types of venues eg. Market, hospital, Zoo
        RecyclerView rvVenueCategory = findViewById(R.id.rvVenueCategory);

        AdapterForVenueCategory adapter = new AdapterForVenueCategory(getCategoryData());

        rvVenueCategory.setLayoutManager(new LinearLayoutManager(this));
        rvVenueCategory.setAdapter(adapter);

        //////////////// on Clicking Type of Venue (eg. Market)////////////////////
        adapter.setOnItemClickListener(new AdapterForVenueCategory.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                String text = ((TextView) view.findViewById(R.id.venueCategory)).getText().toString();
                Toast.makeText(ChooseVenueType.this, text, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ChooseVenueType.this, DisplayVenues.class);
                intent.putExtra("VENUE_TYPE", text);
                intent.putExtra("CITY_NAME", cityName);
                startActivity(intent);
            }
        });

    }


    /**
     * @return ArrayList<Pair<String, Integer>>
     */
    ArrayList<Pair<String, Integer>> getCategoryData(){
        ArrayList<Pair<String, Integer>> list = new ArrayList<>();

        list.add(new Pair<>("Market", R.drawable.market));
        list.add(new Pair<>("Hospital", R.drawable.doctor));
        list.add(new Pair<>("Historic Site", R.drawable.historic_site));
        list.add(new Pair<>("Cinema", R.drawable.movie_theater));
        list.add(new Pair<>("Museum", R.drawable.museum));
        list.add(new Pair<>("Stadium", R.drawable.stadium));
        list.add(new Pair<>("Water Park", R.drawable.water_park));
        list.add(new Pair<>("Zoo", R.drawable.zoo));
        list.add(new Pair<>("College", R.drawable.college));
        list.add(new Pair<>("Food", R.drawable.food));
        list.add(new Pair<>("Cafe", R.drawable.cafe));
        list.add(new Pair<>("Night Life Spot", R.drawable.night_life_spot));
        list.add(new Pair<>("Public Park", R.drawable.park));
        list.add(new Pair<>("Rivers", R.drawable.river));
        list.add(new Pair<>("Police", R.drawable.police));
        list.add(new Pair<>("Government Building", R.drawable.government_building));
        list.add(new Pair<>("Temple", R.drawable.spiritual_site));
        list.add(new Pair<>("Shopping Mall", R.drawable.shopping_mall));
        list.add(new Pair<>("Transport", R.drawable.travel_and_transport));

        return list;
    }
}