package com.example.hangmanv5;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GameResultFragment extends Fragment { //this fragment does not have an XML

    TextView TXT_gameResult;

    public GameResultFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUpFragmentGUI(container);
        return super.onCreateView(inflater, container, savedInstanceState); //typing onCreateView brought up the following 3
    }

    private void setUpFragmentGUI(ViewGroup container) {
        if(TXT_gameResult == null) { //if it doesn't exist we want a new one
            TXT_gameResult= new TextView(getActivity());
            TXT_gameResult.setGravity(Gravity.CENTER);
            container.addView(TXT_gameResult);
        }
    }

    public void onStart() {
        super.onStart();
        TXT_gameResult.setText("Good Luck!");
    }

    public void setResult(String result) {
        TXT_gameResult.setText(result);
    }
}
