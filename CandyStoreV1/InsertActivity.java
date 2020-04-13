package com.example.candystorev1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }
    //add button
    public void insert(View v) {
        Log.w("InsertActivity", "insert button pressed");

        // Retrieve name and price
        EditText nameEDIT = findViewById(R.id.INPUT_name);
        EditText priceEDIT = findViewById(R.id.INPUT_price);
        String nameString = nameEDIT.getText().toString(); //get entered text & turn it into a string
        String priceString = priceEDIT.getText().toString();

        // insert new candy in database (later version)

        // clear data
        nameEDIT.setText( "" );
        priceEDIT.setText( "" );
    }

    //back button
    public void goBack(View v) {
        this.finish(); //ends activity
    }

}
