package com.example.tiporientation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f, 100);
        setContentView(R.layout.activity_main);
        connectWatchers();
    }

    private void modifyLayout(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_main_landscape); //we create new XML for this layout
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
        connectWatchers();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Log.w("MainActivity", "Inside onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig); //if orientation changes again, send back to modifyLayout
    }

    private void connectWatchers() { //makes both layouts work!!!
        billEditText = findViewById(R.id.EDIT_BillAmount);
        tipEditText = findViewById(R.id.EDIT_EnterTip);
        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
    }

    public void calculate() {
        //convert edit texts to a string ???
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        //2 text views from XML
        TextView tipTextView = findViewById(R.id.TXT_TipTotal);
        TextView totalTextView = findViewById(R.id.TXT_TotalAmount);

        try {
            //convert billString to float & tipString to int -- can't do math with strings
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            //update the model -- referencing TipCalculator class!
            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);
            //ask model to calculate
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            //update view with formatted tip & total amount
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        } catch (NumberFormatException nfe) {
        }
    }

    //create implements... select all 3 they are then stubbed out in the class
    private class TextChangeHandler implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
             }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        @Override
        public void afterTextChanged(Editable s) {
            calculate();
             }
    }
}
