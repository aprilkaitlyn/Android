package com.example.todolistv1;

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

        // Retrieve item to do
        EditText itemEDIT = findViewById(R.id.itemEDIT);
        String item = itemEDIT.getText().toString(); //get entered text & turn it into a string

        // insert new to do item in database
       try {
           ToDo todo = new ToDo (0, item);
            dbManager.insert(todo);
            Toast.makeText(this, "to do added", Toast.LENGTH_SHORT).show();
       }
        catch (NumberFormatException nfe) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
        }

        // clear data
        itemEDIT.setText("");
    }

    //back button
    public void goBack(View v) {
        this.finish(); //ends activity
    }

}
