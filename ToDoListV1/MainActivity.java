package com.example.todolistv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
    }

    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        ArrayList<ToDo> todos = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (ToDo todo : todos) {
            TextView list = new TextView(this); //allows items to be checked off
            list.setId(todo.getId());
            list.setText("✽ " + todo.toString());
            list.setTextSize(25);
            group.addView(list);
        }

        scrollView.addView(group);
        layout.addView(scrollView);

        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.setPadding(50,50,50,50);
        setContentView(layout);
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

        switch (id) {
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
