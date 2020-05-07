package com.example.TaxiCalculator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class DatabaseFragment extends Fragment {

    DatabaseManager dbManager; //link to database
    
    public DatabaseFragment() { //required empty method
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        updateView(container);
        return super.onCreateView(inflater, container, savedInstanceState); 
    }

    private void updateView(ViewGroup container) { //creates view that displays database
        dbManager = new DatabaseManager(getContext());
        ArrayList<Taxi> taxies = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(getContext());
        ScrollView scrollView = new ScrollView(getContext());
        RadioGroup group = new RadioGroup(getContext());
        for (Taxi taxi : taxies) {
            TextView taxiList = new TextView(getContext());
            taxiList.setId(taxi.getId());
            taxiList.setText("  " + taxi.getName() + "      $" + taxi.getBasePrice() + "       $" + taxi.getPricePerMile());
            group.addView(taxiList);
        }

        scrollView.addView(group);
        layout.addView(scrollView);

        container.addView(layout);
    }
}
