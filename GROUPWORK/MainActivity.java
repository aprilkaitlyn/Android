package com.example.wonderfulproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TaxiMath taxi;
    DatabaseManager dbManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            taxi = new TaxiMath();
            dbManager = new DatabaseManager(this);


        //FragmentManager gives the tools for working with fragments
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.FRAG_info) == null) { //checking that the fragment exists
            FragmentTransaction ft = fm.beginTransaction();
            EnterInfoFragment fragment = new EnterInfoFragment();
            ft.add(R.id.FRAG_info, fragment);
            ft.commit(); //need this for fragment to be visible
        }

        if (fm.findFragmentById(R.id.FRAG_database) == null) { //copied for game result fragment
            FragmentTransaction ft = fm.beginTransaction();
            DatabaseFragment fragment = new DatabaseFragment();
            ft.add(R.id.FRAG_database, fragment);
            ft.commit(); //need this for fragment to be visible
        }
    }

    private void createView() { //same as update activity but no ability to edit
        ArrayList<Taxi> taxis = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (Taxi taxi : taxis) {
            TextView friendList = new TextView(this);
            taxiList.setId(taxi.getId());
            taxiList.setText(taxi.get() + taxi.get() + taxi.get()); //concatenate name + base price + price per mile
            taxiList.setTextSize(20);
            group.addView(taxiList);
        }

        scrollView.addView(group);
        layout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.setPadding(50,50,50,50);

        setContentView(layout);
    }
}
