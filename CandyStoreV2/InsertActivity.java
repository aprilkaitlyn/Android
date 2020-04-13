package com.example.candystorev2;

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
        setContentView(R.layout.activity_insert);
    }
    //add button
    public void insert(View v) {
        Log.w("InsertActivity", "insert button pressed");

        // Retrieve name and price
        EditText nameEDIT = findViewById(R.id.INPUT_name);
        EditText priceEDIT = findViewById(R.id.INPUT_price);
        String name = nameEDIT.getText().toString(); //get entered text & turn it into a string
        String priceString = priceEDIT.getText().toString();

        // insert new candy in database
       try {
            double price = Double.parseDouble(priceString); //parse so we can do math with it
            Candy candy = new Candy (0, name, price);
            dbManager.insert(candy);
            Toast.makeText(this, "candy added", Toast.LENGTH_SHORT).show();
       }
        catch (NumberFormatException nfe) { //if incorrectly typed in for example 1.0.0
            Toast.makeText(this, "price error", Toast.LENGTH_LONG ).show();
        }

        // clear data
        nameEDIT.setText("");
        priceEDIT.setText("");
    }

    //back button
    public void goBack(View v) {
        this.finish(); //ends activity
    }

}
