package com.example.TaxiCalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class EnterInfoFragment extends Fragment {
    

    public EnterInfoFragment() { //required empty method
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enter_info, container, false);    }
        //link to fragment's xml
}
