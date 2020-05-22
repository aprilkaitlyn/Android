package com.example.hellogoodbye;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton TOGGLE;
    ImageView helloImage;
    ImageView byeImage;
    TextView helloText;
    TextView byeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton TOGGLE = findViewById(R.id.TOGGLE);
        helloImage = findViewById(R.id.hello_iv);
        byeImage = findViewById(R.id.goodbye_iv);
        helloText = findViewById(R.id.Hello_tv);
        byeText = findViewById(R.id.Goodbye_tv);

        TOGGLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        {
                if (TOGGLE.isChecked()) {
                    helloImage.setVisibility(View.INVISIBLE);
                    helloText.setVisibility(View.INVISIBLE);
                    byeImage.setVisibility(View.VISIBLE);
                    byeText.setVisibility(View.VISIBLE);
                } else {
                    byeImage.setVisibility(View.INVISIBLE);
                    byeText.setVisibility(View.INVISIBLE);
                    helloImage.setVisibility(View.VISIBLE);
                    helloText.setVisibility(View.VISIBLE);
                }
            }
        }

        });
}}
