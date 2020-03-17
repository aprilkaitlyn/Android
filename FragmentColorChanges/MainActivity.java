package com.example.fragmentcolorchanges;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public boolean isStop = true;
    public boolean isGo = false;
    public boolean isWait = false;


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
        Button GREEN = findViewById(R.id.GREEN);
        Button RED = findViewById(R.id.RED);
        Button YELLOW = findViewById(R.id.YELLOW);

               if (isStop) { //green
                    isStop = false;
                    isGo = true;
                    isWait = false;
                    GREEN.setVisibility(View.VISIBLE);
                    RED.setVisibility(View.INVISIBLE);
                    YELLOW.setVisibility(View.INVISIBLE);
                } else if (isGo) { //yellow
                    isStop = false;
                    isGo = false;
                    isWait = true;
                   YELLOW.setVisibility(View.VISIBLE);
                   RED.setVisibility(View.INVISIBLE);
                   GREEN.setVisibility(View.INVISIBLE);
                } else { //red
                    isStop = true;
                    isGo = false;
                    isWait = false;
                   RED.setVisibility(View.VISIBLE);
                   GREEN.setVisibility(View.INVISIBLE);
                   YELLOW.setVisibility(View.INVISIBLE);
                }
            }
        }
