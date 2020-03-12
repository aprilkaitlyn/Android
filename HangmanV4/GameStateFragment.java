package com.example.hangmanv4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class GameStateFragment extends Fragment {

    public GameStateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_state, container, false);
    }

    public void onStart() {
        super.onStart();

        View fragmentView = getView(); //"getting primary view"
        TextView gameStateTV = fragmentView.findViewById(R.id.state_of_game);
        MainActivity fragmentActivity = (MainActivity) getActivity();
        gameStateTV.setText(fragmentActivity.getGame().currentIncompleteWord());
    }

}
