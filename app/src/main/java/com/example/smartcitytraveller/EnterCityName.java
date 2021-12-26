package com.example.smartcitytraveller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class EnterCityName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_city_name);

        AutoCompleteTextView cityInput = findViewById(R.id.cityInput);
        Button nxtButton = findViewById(R.id.btnNxt);

        //////////// auto complete feature ////////////
        String[] cities = getResources().getStringArray(R.array.cities);
        cityInput.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, cities));
        cityInput.setThreshold(1);
        ////////////                      ////////////

        // on clicking next button
        nxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fetch user input
                String cityName = String.valueOf(cityInput.getText());
                //if there is no input
                if(cityName.isEmpty()){
                    Toast.makeText(EnterCityName.this, "Please enter city", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(EnterCityName.this, ChooseVenueType.class);
                    intent.putExtra("CITY_NAME", cityName);
                    startActivity(intent);
                }
            }
        });
    }
}