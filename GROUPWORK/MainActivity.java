package com.example.wonderfulproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TaxiMath taxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            taxi = new TaxiMath();

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
}
