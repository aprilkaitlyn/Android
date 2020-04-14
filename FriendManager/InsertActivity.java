package com.example.friendmanager;

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

        // Retrieve name and email
        EditText firstnameEDIT = findViewById(R.id.INPUT_firstName);
        EditText lastnameEDIT = findViewById(R.id.INPUT_lastName);
        EditText emailEDIT = findViewById(R.id.INPUT_email);
        String firstname = firstnameEDIT.getText().toString(); //get entered text & turn it into a string
        String lastname = lastnameEDIT.getText().toString();
        String email = emailEDIT.getText().toString();

        // insert new friend in database
       try {
           Friend friend = new Friend (0, firstname, lastname, email);
            dbManager.insert(friend);
            Toast.makeText(this, "friend added", Toast.LENGTH_SHORT).show();
       }
        catch (NumberFormatException nfe) { 
            Toast.makeText(this, "error", Toast.LENGTH_LONG ).show();
        }

        // clear data
        firstnameEDIT.setText("");
        lastnameEDIT.setText("");
        emailEDIT.setText("");
    }

    //back button
    public void goBack(View v) {
        this.finish(); //ends activity
    }

}
