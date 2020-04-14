package com.example.friendmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        createView(); // making view here not xml
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu (tells Android about the menu we created)
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle action bar items here
        int id = item.getItemId(); // lets us use our menu buttons in a switch statement

        switch(id) {
            case R.id.action_add:
                Log.w("MainActivity", "add selected");
                Intent insertIntent = new Intent(this, InsertActivity.class); //InsertActivity
                //add the activity to the manifest!!!
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "delete selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class); //DeleteActivity
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "update selected");
                Intent updateIntent = new Intent(this, UpdateActivity.class); //UpdateActivity
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createView() { //same as update activity but no ability to edit
        ArrayList<Friend> friends = dbManager.selectAll();

        // create ScrollView and GridLayout
        if (friends.size()>0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friends.size());
            grid.setColumnCount(4); //now 4 because no button

            // create arrays of components
            TextView[] ids = new TextView[friends.size()];
            TextView[][] namesAndEmail = new TextView[friends.size()][100];

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            // create the TextView for the candy's id
            int i = 0;

            for (Friend friend : friends) {

                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friend.getId());

                // create the 3 text views for the friend's name and email
                namesAndEmail[i][0] = new TextView(this);
                namesAndEmail[i][1] = new TextView(this);
                namesAndEmail[i][2] = new TextView(this);
                namesAndEmail[i][0].setText(friend.getFirstName());
                namesAndEmail[i][1].setText(friend.getLastName());
                namesAndEmail[i][2].setText(friend.getEmail());
                namesAndEmail[i][0].setTextSize(15);
                namesAndEmail[i][1].setTextSize(15); //small font to fit
                namesAndEmail[i][2].setTextSize(15);
                namesAndEmail[i][0].setId(10 * friend.getId());
                namesAndEmail[i][1].setId(10 * friend.getId() + 1);
                namesAndEmail[i][2].setId(10 * friend.getId() + 2);

                // add the elements to grid - changed these
                grid.addView(ids[i], (int) (width * .05), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmail[i][0], (int) (width * .2), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmail[i][1], (int) (width * .3), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmail[i][2], (int) (width * .4), ViewGroup.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }
}
