package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    Button [] [] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildGUIByCode();
    }

    private void buildGUIByCode() {
        //get width of screen using points & import Point
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x/TicTacToe.SIDE;

        //create layout manager as a GridLayout -- "this" means current context??
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(TicTacToe.SIDE);
        grid.setRowCount(TicTacToe.SIDE);

        //create buttons & add to GridLayout - column = col
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++){
                buttons[row][col] = new Button(this);
                grid.addView(buttons[row][col],w,w); //without addView buttons won't show up on screen
            }
        }

        //set GridLayout as the view of this activity
        setContentView(grid);
    }
}
