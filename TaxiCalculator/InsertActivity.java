package com.example.TaxiCalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    private DatabaseManager dbManager; // DatabaseManager class reference

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this); //connecting DatabaseManager class
        setContentView(R.layout.activity_insert); //layout
    }

    //add button
    public void insert(View v) {
        Log.w("InsertActivity", "insert button pressed");

        // Retrieve name, base cost & cost per mile
        EditText nameEDIT = findViewById(R.id.INPUT_name);
        EditText baseCostEDIT = findViewById(R.id.INPUT_baseCost);
        EditText perMileEDIT = findViewById(R.id.INPUT_perMile);

        //get entered text & turn it into a string
        String name = nameEDIT.getText().toString(); 
        String baseCost = baseCostEDIT.getText().toString();
        String perMile = perMileEDIT.getText().toString();

        // insert new taxi in database
        try {
            double base = Double.parseDouble(baseCost); //parse so we can do math with it
            double per = Double.parseDouble(perMile); //parse so we can do math with it
            Taxi taxi = new Taxi (0, name, base, per); //according to taxi database
            dbManager.insert(taxi);
            Toast.makeText(this, "taxi added", Toast.LENGTH_SHORT).show(); //toast = success
        }
        catch (NumberFormatException nfe) { //if incorrectly typed in for example 1.0.0
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }

        // clear data
        baseCostEDIT.setText("");
        perMileEDIT.setText("");
        nameEDIT.setText("");
    }

    //back button
    public void goBack(View v) {
        this.finish(); //ends activity
    }
}
