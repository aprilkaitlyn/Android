package com.example.threadingdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //note: don't put anything visible in the UI in a thread, may get out of sync based on loading times

    ImageView bear;
    TextView tv;
    Handler waitHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) { //auto generated
            tv = findViewById(R.id.TXT_bearlogo);
            tv.setText("Go Missouri State :)"); //text changes after 10 sec
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bear = findViewById(R.id.IMG_logo);
    }

    public void clickMe(View view) {

        Runnable r = new Runnable() { //a runnable is a "container"
            @Override
            public void run() {
                long future = (System.currentTimeMillis() + 10000); //10 sec timer (10,000 milliseconds)
                while (System.currentTimeMillis() < future) { //while we wait for the timer...
                    try { wait(future - System.currentTimeMillis());
                    } catch (Exception e) { }
                }
                waitHandler.sendEmptyMessage(0);
            }
        };

        Thread t = new Thread(r); //pass our runnable r in our thread
        t.start(); //start the thread

        bear.setVisibility(View.VISIBLE); //will pop up right away on click
    }
}
