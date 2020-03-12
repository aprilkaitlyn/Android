package com.example.hangmanv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


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
        FragmentTransaction ft = fm.beginTransaction();
        GameStateFragment fragment = new GameStateFragment();
        ft.add(R.id.game_state, fragment);
        ft.commit(); //need this for fragment to be visible
    }

    public void play(View view) { //play button's onclick event

    }

    public Hangman getGame() { //method from GameStateFragment
        return game;
    }
}
