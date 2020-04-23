package com.example.burger;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //XML variables
    private RadioGroup PattyRadioGroup;
    private CheckBox ProsciuttoCheckBox;
    private RadioGroup CheeseRadioGroup;
    private SeekBar SauceSeekBar;
    private TextView CaloriesText;

    //Burger class
    private Burger burger;

    //auto generated onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        burger = new Burger();
        initialize();

        registerChangeListener();
    }

    private void initialize() {
        // connect XML elements
                PattyRadioGroup = findViewById(R.id.RADIOGRP_Patty);
                ProsciuttoCheckBox = findViewById(R.id.CHKBOX_Prosciutto);
                CheeseRadioGroup = findViewById(R.id.RADIOGRP_Cheese);
                SauceSeekBar = findViewById(R.id.SEEK_Sauce);
                CaloriesText = findViewById(R.id.TXT_Calories);
        //method call
        displayCalories();
    }

    private void registerChangeListener() {
        PattyRadioGroup.setOnCheckedChangeListener(foodListener);
        ProsciuttoCheckBox.setOnClickListener(baconListener);
        CheeseRadioGroup.setOnCheckedChangeListener(foodListener);
        SauceSeekBar.setOnSeekBarChangeListener(sauceListener);
    }

    private OnCheckedChangeListener foodListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup rbGroup, int radioId) {
            RadioButton rb = findViewById(radioId);
            String btn = rb.getText().toString();

            switch (btn) {
                case "Beef Patty": // BEEF PATTY
                    burger.setPattyCalories(Burger.BEEF);
                    break;
                case "Lamb Patty": // LAMB PATTY
                    burger.setPattyCalories(Burger.LAMB);
                    break;
                case "Ostrich Patty": // OSTRICH PATTY
                    burger.setPattyCalories(Burger.OSTRICH);
                    break;
                case "Asiago Cheese": // ASIAGO CHEESE
                    burger.setCheeseCalories(Burger.ASIAGO);
                    break;
                case "Creme Fraiche": // CREME FRAICHE
                    burger.setCheeseCalories(Burger.CREME_FRAICHE);
            }
            displayCalories();
        }
    };

    private OnClickListener baconListener = new OnClickListener() {
        public void onClick(View v) {
            if (((CheckBox) v).isChecked())
                burger.setProsciuttoCalories(Burger.PROSCIUTTO);
            else
                burger.clearProsciuttoCalories();

            displayCalories();
        }
    };

    private OnSeekBarChangeListener sauceListener = new OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            burger.setSauceCalories(seekBar.getProgress());
            // sauceCal = seekBar.getProgress();
            displayCalories();
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private void displayCalories() {

        // CONSTRUCT AN OUTPUT STRING AND DISPLAY IN THE TEXTVIEW
        String calorieText = "Calories: " + burger.getTotalCalories();
        CaloriesText.setText(calorieText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
