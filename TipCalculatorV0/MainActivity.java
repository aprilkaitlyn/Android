package com.example.tipcalculatorv0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();

    //auto-generated onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f,100);
        setContentView(R.layout.activity_main);
    }

    //calculate class called when user clicks *calculate* button -- assigned as onClick in XML
    public void calculate (View v) {
        Log.w("Main Activity", "v = " + v);

        //2 edit texts from XML
        EditText billEditText = findViewById(R.id.EDIT_BillAmount);
        EditText tipEditText = findViewById(R.id.EDIT_EnterTip);

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

} catch(NumberFormatException nfe) { }

    }
}
