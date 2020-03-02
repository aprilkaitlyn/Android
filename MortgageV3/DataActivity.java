package com.example.mortgagecalc3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        updateDataActivityView();
    }

    private void updateDataActivityView() { //grab entered data from first activity
        Mortgage mortgage = MainActivity.mortgage; //reference to mortgage in MainActivity

        if(mortgage.getYears() == 10 ) { //this if else is copied from github
            RadioButton rb10 = findViewById( R.id.BTN_10);
            rb10.setChecked(true);
        }
        else if(mortgage.getYears() == 15 ) {
            RadioButton rb15 = findViewById( R.id.BTN_15);
            rb15.setChecked(true);
        } // else do nothing (default is 30)

        EditText amountEDIT = findViewById(R.id.EDIT_amount);
        amountEDIT.setText("" + mortgage.getAmount());
        EditText rateEDIT = findViewById(R.id.EDIT_rate);
        rateEDIT.setText("" + mortgage.getRate());
    }

    private void updateMortgage() { //update the data to 1st activity
        Mortgage mortgage = MainActivity.mortgage; //same reference from MainActivity

        RadioButton RB10 = findViewById(R.id.BTN_10);
        RadioButton RB15 = findViewById(R.id.BTN_15);

        int years = 30;
        if (RB10.isChecked()) years = 10;
        else if (RB15.isChecked()) years = 15;

        mortgage.setYears(years);
        EditText amountEDIT = findViewById( R.id.EDIT_amount);
        String amountString = amountEDIT.getText().toString();
        EditText rateEDIT =findViewById( R.id.EDIT_rate);
        String rateString = rateEDIT.getText().toString();

        try { //bc there could be an error??
            float amount = Float.parseFloat(amountString);
            mortgage.setAmount(amount);
            float rate = Float.parseFloat(rateString);
            mortgage.setRate(rate);
            mortgage.setPreferences(this);
        } catch(NumberFormatException nfe) {
            mortgage.setAmount(100000.0f);
            mortgage.setRate(.035f);
        }

    }

    public void goBack(View view) {  //onclick activity for 2nd activity's button
        updateMortgage();
        this.finish();  //this ends the 2nd activity, thus taking you back where you came from
    }


}
