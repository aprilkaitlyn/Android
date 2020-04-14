package com.example.friendmanager;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    //building View here dynamically because we don't know how much will be returned with the database
    private void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();

        // create ScrollView and GridLayout
        if (friends.size()>0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(friends.size());
            grid.setColumnCount(5);

            // create arrays of components
            TextView[] ids = new TextView[friends.size()];
            EditText[][] namesAndEmail = new EditText[friends.size()][3];
            Button[] buttons = new Button[friends.size()];
            ButtonHandler handler = new ButtonHandler();

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            // create the TextView for the friend's id
            int i = 0;

            for (Friend friend : friends) {

                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + friend.getId());

                // create the 3 EditTexts for the friend's name and email
                namesAndEmail[i][0] = new EditText(this);
                namesAndEmail[i][1] = new EditText(this);
                namesAndEmail[i][2] = new EditText(this);
                namesAndEmail[i][0].setText(friend.getFirstName());
                namesAndEmail[i][1].setText(friend.getLastName());
                namesAndEmail[i][2].setText(friend.getEmail());
                namesAndEmail[i][0].setTextSize(14);
                namesAndEmail[i][1].setTextSize(14); //small font to fit
                namesAndEmail[i][2].setTextSize(14);
                namesAndEmail[i][0].setId(10 * friend.getId());
                namesAndEmail[i][1].setId(10 * friend.getId() + 1);
                namesAndEmail[i][2].setId(10 * friend.getId() + 2);

                // create the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(friend.getId());
                buttons[i].setTextSize(9);
                // set up event handling
                buttons[i].setOnClickListener(handler);

                // add the elements to grid - changed these to make all columns fit
                grid.addView(ids[i], (int) (width * .05), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmail[i][0], (int) (width * .2), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmail[i][1], (int) (width * .2), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndEmail[i][2], (int) (width * .4), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);

        }
    }


    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // retrieve name and email of friend
            int friendId = v.getId();
            EditText firstnameET = findViewById(10 * friendId);
            EditText lastnameET = findViewById(10 * friendId + 1);
            EditText emailET = findViewById(10 * friendId + 2);
            String firstname = firstnameET.getText().toString();
            String lastname = lastnameET.getText().toString();
            String email = emailET.getText().toString();

            // update friend in database
            try {
                dbManager.updateById(friendId, firstname, lastname, email);
                Toast.makeText(UpdateActivity.this, "friend updated", Toast.LENGTH_SHORT).show();
                // update screen
                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        }
    }
}
