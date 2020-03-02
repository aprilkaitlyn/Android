package com.example.autopurchasee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class LoanSummaryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loansummary_layout);
        TextView monthlyPayTV = findViewById(R.id.monthlyPayTV);
        TextView loanReportTV = findViewById(R.id.loanReportTV);

        // PASS DATA
        Intent intent = getIntent();

        String report;
        report = intent.getStringExtra("LoanReport");

        String monthlyPay;
        monthlyPay = intent.getStringExtra("MonthlyPayment");
        monthlyPayTV.setText(monthlyPay);
        loanReportTV.setText(report);
    }

    public void goDataEntry(View view) {
        finish();
    }
}
