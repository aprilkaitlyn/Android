package com.example.trafficlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //declare colors
    public static final int RED = 0xffff0000;
    public static final int YELLOW = 0xffffff00;
    public static final int GREEN = 0xff00ff00;

    //declare boolean states
    public boolean isStop = true;
    public boolean isGo = false;
    public boolean isWait = false;

    //auto generated onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect XML elements
        final Button BUTTON = findViewById(R.id.BUTTON);
        final ImageView redlight = findViewById(R.id.redlight);
        final ImageView yellowlight = findViewById(R.id.yellowlight);
        final ImageView greenlight = findViewById(R.id.greenlight);

        //button's function
        BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStop) { //green light
                    BUTTON.setBackgroundColor(GREEN);
                    BUTTON.setText("Go");
                    isStop = false;
                    isGo = true;
                    isWait = false;
                    redlight.setVisibility(View.INVISIBLE);
                    yellowlight.setVisibility(View.INVISIBLE);
                    greenlight.setVisibility(View.VISIBLE);
                } else if (isGo) { //yellow light
                    BUTTON.setBackgroundColor(YELLOW);
                    BUTTON.setText("Wait");
                    isStop = false;
                    isGo = false;
                    isWait = true;
                    redlight.setVisibility(View.INVISIBLE);
                    yellowlight.setVisibility(View.VISIBLE);
                    greenlight.setVisibility(View.INVISIBLE);
                } else { //red light
                    BUTTON.setBackgroundColor(RED);
                    BUTTON.setText("Stop");
                    isStop = true;
                    isGo = false;
                    isWait = false;
                    redlight.setVisibility(View.VISIBLE);
                    yellowlight.setVisibility(View.INVISIBLE);
                    greenlight.setVisibility(View.INVISIBLE);
                }
            }

        });
    }
}
