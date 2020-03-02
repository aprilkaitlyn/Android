package com.example.backandforth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class second_layout extends AppCompatActivity {
String counter;
int CountInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the layout we created
        setContentView(R.layout.activity_second_layout);
    }

    public void updateNumber (View view) {
        Intent goBackAndForth = getIntent();
        counter = goBackAndForth.getStringExtra("count");
        CountInteger = Integer.valueOf(counter);
        CountInteger = CountInteger + 1;
        counter = Integer.toString(CountInteger);
        Intent passBack = new Intent();
        passBack.putExtra("countOne", counter);
        setResult(RESULT_OK, passBack);
        this.finish();
    }

}
