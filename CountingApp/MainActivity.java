package com.example.countingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button tapbtn;
    TextView counttv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tapbtn = findViewById(R.id.tap_btn);
        counttv = findViewById(R.id.count_tv);


        tapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countNumber = counttv.getText().toString();
                int countNumberInteger = Integer.parseInt(countNumber);
                countNumberInteger++;

                counttv.setText(String.valueOf(countNumberInteger));
            }
        });
    }
}
