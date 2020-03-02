package com.example.mortgagecalc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


//this model will switch between activities, clear fields on open
public class MainActivity extends AppCompatActivity {
    public static Mortgage mortgage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mortgage = new Mortgage();
        setContentView(R.layout.activity_main);
    }

    protected void onStart() {  //runs every time app is started
        super.onStart();
        updateView();
    }

    private void updateView() {
        TextView amountTXT = findViewById(R.id.TXT_amount);
                 amountTXT.setText(Mortgage.getFormattedAmount());
        TextView years = findViewById(R.id.TXT_years);
                 years.setText("" + Mortgage.getYears());
        TextView rateTXT = findViewById(R.id.TXT_rate);
                 rateTXT.setText(100 * Mortgage.getRate() + "%");
        TextView payment = findViewById(R.id.TXT_payment);
                 payment.setText(Mortgage.formattedMonthlyPayment());
        TextView total = findViewById(R.id.TXT_total);
                 total.setText(Mortgage.formattedTotalPayment());
    }

    public void modifyData(View view) {
        //just these 2 lines will switch to other activity!!!
        Intent myIntent = new Intent(this, DataActivity.class);
        this.startActivity(myIntent);
        //intent is message between activities

    }
}

