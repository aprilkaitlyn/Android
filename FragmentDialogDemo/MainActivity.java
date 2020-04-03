package com.example.fragmentdialogdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //to make our menu... res --> new --> Android Resource Directory --> change value to menu
    // -->menu will appear under res --> new --> Menu Resource File --> makes an XML for the menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //right click --> generate --> override method --> search onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflate the menu, use R.menu NOT R.id & link it to my_menu
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true; //replaced generated return statement with "true"
    }

    //right click --> generate --> override method --> search onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handles action bar clicks... turns the id of button clicked into an int??
        int id = item.getItemId();

        //capture which button is clicked
        if (id == R.id.action_setting) {
            DialogFragment myFragment = new MyDialogFragment(); //calls instance of our new class
            myFragment.show(getSupportFragmentManager(), "theDialog");
            return true;
             }
        else if (id == R.id.exit_app) {
            finish(); //close app
            return true;
             }

        return super.onOptionsItemSelected(item);
    }
}
