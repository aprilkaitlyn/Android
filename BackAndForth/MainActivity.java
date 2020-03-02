package com.example.backandforth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button BTN_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BTN_number = findViewById(R.id.BTN_number);
    }

    public void onGo(View view) {
        final int result = 1;
        String greenButtonString = BTN_number.getText().toString();
        Intent goBackAndForth = new Intent(this, second_layout.class);
        goBackAndForth.putExtra("count", greenButtonString);
        startActivityForResult(goBackAndForth, result);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String newCountString = data.getStringExtra("countOne");
        BTN_number.setText(newCountString);
    }

}
