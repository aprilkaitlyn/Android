package com.example.trafficlightfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.graphics.Color.GREEN;

public class MainActivity extends AppCompatActivity {

    public boolean isStop = true;
    public boolean isGo = false;
    public boolean isWait = false;
    public static final int RED = 0xffff0000;
    public static final int YELLOW = 0xffffff00;
    public static final int GREEN = 0xff00ff00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.leftFragment) == null) { //left / click me fragment
            FragmentTransaction ft = fm.beginTransaction();
            LeftFragment fragment = new LeftFragment();
            ft.add(R.id.leftFragment, fragment);
            ft.commit(); //need this for fragment to be visible
        }

        if (fm.findFragmentById(R.id.rightFragment) == null) { //right / color change fragment
            FragmentTransaction ft = fm.beginTransaction();
            RightFragment fragment = new RightFragment();
            ft.add(R.id.rightFragment, fragment);
            ft.commit(); //need this for fragment to be visible
        }
    }

    public void click (View view) { //left fragment's on click
        Button LABEL = findViewById(R.id.LABEL); //right fragment
                if (isStop) { //green light
                    LABEL.setBackgroundColor(GREEN);
                    LABEL.setText("Green Light");
                    isStop = false;
                    isGo = true;
                    isWait = false;
                } else if (isGo) { //yellow light
                    LABEL.setBackgroundColor(YELLOW);
                    LABEL.setText("Yellow Light");
                    isStop = false;
                    isGo = false;
                    isWait = true;
                } else { //red light
                    LABEL.setBackgroundColor(RED);
                    LABEL.setText("Red Light");
                    isStop = true;
                    isGo = false;
                    isWait = false;
                }
            }
        }
