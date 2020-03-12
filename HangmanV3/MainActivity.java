package com.example.hangmanv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
//added game result fragment + good luck

    Hangman game; //reference to new Hangman class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (game == null) //if there's no game, make a game
            game = new Hangman(Hangman.DEFAULT_GUESSES); //start new game
        setContentView(R.layout.activity_main); //set the view
        TextView status = findViewById(R.id.status); //status text view
        status.setText("" + game.getGuessesLeft()); //set status


        //FragmentManager gives the tools for working with fragments
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.game_state) == null) { //checking that the fragment exists
            FragmentTransaction ft = fm.beginTransaction();
            GameStateFragment fragment = new GameStateFragment();
            ft.add(R.id.game_state, fragment);
            ft.commit(); //need this for fragment to be visible
        }

        if (fm.findFragmentById(R.id.game_result) == null) { //copied for game result fragment
            FragmentTransaction ft = fm.beginTransaction();
            GameResultFragment fragment = new GameResultFragment();
            ft.add(R.id.game_result, fragment);
            ft.commit(); //need this for fragment to be visible
        }
    }

    public void play(View view) { //play button's onclick event

    }

    public Hangman getGame() { //method from GameStateFragment
        return game;
    }
}
