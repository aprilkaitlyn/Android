package com.example.threadinghomework;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView bear;
    long timeInMilliseconds, startTime;
    int timeInSeconds;
    TextView timer2;
    TextView waitover;
    Handler waitHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) { //auto generated
            waitover = findViewById(R.id.tv_waitforit);
            waitover.setVisibility(View.VISIBLE); //text appears after 10 sec
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bear = findViewById(R.id.iv_bearLogo);
        timer2 = findViewById(R.id.tv_timer2);
        waitover = findViewById(R.id.tv_waitforit);
    }

    public void clickTheButton(View view) {

        Runnable r = new Runnable() { //a runnable is a "container"

            @Override
            public void run() {
                long futureTime = (System.currentTimeMillis() + 10000); //10 sec timer
                while (System.currentTimeMillis() < futureTime) {
                    try {
                        wait(futureTime - System.currentTimeMillis());
                    } catch (Exception e) { }
                    }
                waitHandler.sendEmptyMessage(0);
            }
        };

        Thread t = new Thread(r); //pass our runnable r in our thread
        t.start(); //start the thread

        startTime = SystemClock.uptimeMillis();
        bear.setVisibility(View.VISIBLE);
        timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
        timeInSeconds = (int) (timeInMilliseconds / 1000);
        timer2.setVisibility(View.VISIBLE); //now happens instantly
        timer2.setText("Elapsed Time = " + "MilliSeconds " + String.format("%02d", timeInMilliseconds)
                + " or " + "Seconds: " + String.format("%02d", timeInSeconds));
    }
}
