package com.example.todolistv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbManager; //same reference to DatabaseManager class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    //build a view with all of the to do items
    private void updateView() {
        ArrayList<ToDo> todos = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (ToDo todo : todos) {
            RadioButton rb = new RadioButton(this); //allows items to be checked off
            rb.setId(todo.getId());
            rb.setText(todo.toString() + " | due: " +todo.getDueDate());
            rb.setTextSize(25);
            group.addView(rb);
        }

        // set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        // create a back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteActivity.this.finish(); //finishes the delete activity, goes back to main view
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);

        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backButton, params);
        layout.setPadding(50,50,50,50);
        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // delete candy from database
            dbManager.deleteById(checkedId);
            Toast.makeText(DeleteActivity.this, "item deleted", Toast.LENGTH_SHORT).show();

            // update screen
            updateView();
        }
    }
}
