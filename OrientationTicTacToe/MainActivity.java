package com.example.orientationtictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TicTacToe tttGame;
    private Button[][] buttons;
    private TextView status;
    boolean horizontalDimensionsSet;
    boolean verticalDimensionsSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tttGame = new TicTacToe();
        // instead of doing everything in XML
        Configuration config = getResources().getConfiguration();
        setupGUI(config);
    }

    public void setupGUI(Configuration newConfig) {
        // Get width of the screen
        // TicTacToe.SIDE is a constant value of 3
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIDE; // for portrait
        int x = size.x / 10; //for landscape

        // Create the layout manager as a GridLayout
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(TicTacToe.SIDE);
        grid.setRowCount(TicTacToe.SIDE + 1); //4th row is for results box

        // Create the buttons and add them to gridLayout

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) { //HORIZONTAL
            grid.setPadding(750,20,0,0); //centers grid
            buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
            ButtonHandler bh = new ButtonHandler();
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int col = 0; col < TicTacToe.SIDE; col++) {
                    buttons[row][col] = new Button(this);
                    buttons[row][col].setTextSize((int) (x * .2));
                    buttons[row][col].setOnClickListener(bh);
                    grid.addView(buttons[row][col], x, x);
                }
            }
            // set up layout parameters for 4th row of grid
            // create *status* TextView
            status = new TextView(this);
            GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE, 1);
            GridLayout.Spec colSpec = GridLayout.spec(0, TicTacToe.SIDE);
            GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, colSpec);
            status.setLayoutParams(lpStatus);
            horizontalDimensionsSet = true;
            status.setWidth(TicTacToe.SIDE * x);
            status.setHeight(x);
            status.setGravity(Gravity.CENTER);
            status.setBackgroundColor(Color.GREEN);
            status.setTextSize((int) (x * .15));
            status.setText(tttGame.result());
        }

        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ) { //PORTRAIT
            grid.setPadding(0,0,0,0);
            buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
            ButtonHandler bh = new ButtonHandler();
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int col = 0; col < TicTacToe.SIDE; col++) {
                    buttons[row][col] = new Button(this);
                    buttons[row][col].setTextSize((int) (w * .2));
                    buttons[row][col].setOnClickListener(bh);
                    grid.addView(buttons[row][col], w, w);
                }
            }
            // set up layout parameters for 4th row of grid
            // create *status* TextView
            status = new TextView(this);
            GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE, 1);
            GridLayout.Spec colSpec = GridLayout.spec(0, TicTacToe.SIDE);
            GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, colSpec);
            status.setLayoutParams(lpStatus);
            status.setWidth(TicTacToe.SIDE * w);
            status.setHeight(w);
            status.setGravity(Gravity.CENTER);
            status.setBackgroundColor(Color.GREEN);
            status.setTextSize((int) (w * .15));
            status.setText(tttGame.result());
            verticalDimensionsSet = true;
        }

        grid.addView(status);

        // set gridLayout as the View of this Activity
        setContentView(grid);
    }

    public void update(int row, int col) {
        int play = tttGame.play(row, col);
        if (verticalDimensionsSet) // set X & O for vertical orientation
             if (play == 1)
            buttons[row][col].setText("X");
             else if (play == 2 )
            buttons[row][col].setText("O");

        if (horizontalDimensionsSet) // set A & Z for horizontal orientation
            if (play == 1)
                buttons[row][col].setText("A");
            else if (play == 2 )
                buttons[row][col].setText("Z");

        if (tttGame.isGameOver())  // game over, disable buttons
        {
            enableButtons(false);
            status.setBackgroundColor(Color.RED);
            status.setText(tttGame.result());
            showNewGameDialog();
        }
    }

    private void showNewGameDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("This is fun!");
        alert.setMessage("Play again?");
        PlayDialog playAgain = new PlayDialog();
        alert.setPositiveButton("YES", playAgain);
        alert.setNegativeButton("NO", playAgain);
        alert.show(); //makes it pop up
    }

    private void resetButtons() {
        for (int row = 0; row < TicTacToe.SIDE; row++)
            for (int col = 0; col < TicTacToe.SIDE; col++)
                buttons[row][col].setText("");
    }

    public void enableButtons(boolean enabled) {
        for (int row = 0; row < TicTacToe.SIDE; row++)
            for (int col = 0; col < TicTacToe.SIDE; col++)
                buttons[row][col].setEnabled(enabled);
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            for (int row = 0; row < TicTacToe.SIDE; row++)
                for (int column = 0; column < TicTacToe.SIDE; column++)
                    if (v == buttons[row][column])
                        update(row, column);
        }
    }

    private class PlayDialog implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int id) {
            // yes button repeats game
            if (id == -1) {
                tttGame.resetGame();
                enableButtons(true);
                resetButtons();
            }
            // no button closes app
            else if (id == -2) {
                MainActivity.this.finish();
            }
        }
    }
}
