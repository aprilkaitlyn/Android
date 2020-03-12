package com.example.hangmanv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //adding GUI elements in this version
    //we are counting # of clicks

    Hangman game; //reference to new Hangman class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(game == null) //if there's no game, make a game
            game = new Hangman(Hangman.DEFAULT_GUESSES); //start new game
            setContentView(R.layout.activity_main); //set the view
            TextView status = findViewById(R.id.status); //status text view
            status.setText("" + game.getGuessesLeft()); //set status
    }
    public void play(View view) {

    }

}
