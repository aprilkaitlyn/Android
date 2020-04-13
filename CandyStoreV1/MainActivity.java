package com.example.candystorev1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //in this version we change the add, delete, update buttons to icons in menu_main.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu (tells Android about the menu we created)
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle aciton bar items here
        int id = item.getItemId(); // lets us use our menu buttons in a switch statement

        switch(id) {
            case R.id.action_add:
                Log.w("MainActivity", "add selected");
                Intent insertIntent = new Intent(this, InsertActivity.class); //new class
                //add the activity to the manifest!!!
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "delete selected");
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "update selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
