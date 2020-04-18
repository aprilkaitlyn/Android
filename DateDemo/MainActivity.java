package com.bateman.testtimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Date date1, date2;
    EditText etDate;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //create a date string. Date 1
        String strDate1= new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());

        //get hold of textview.
        TextView date  = (TextView) findViewById(R.id.tv_cuurentDate);

        //set it as current date.
        date.setText(strDate1);

        sdf = new SimpleDateFormat("MM/dd/yyyy");
        date1 = new Date();
            try {
                date1 = sdf.parse(strDate1);  // Create a date object
            } catch (ParseException e) {
                e.printStackTrace();
            }

        etDate = findViewById(R.id.et_date);
    }

    // click event
    public void compute(View view) {

        String strDate2 = etDate.getText().toString();
        date2 = new Date ();
        try {
            date2 = sdf.parse(strDate2);    // create a second date object
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int diff = date1.compareTo(date2);  // compare the dates

        TextView tvDiff = findViewById(R.id.tv_diff);
        tvDiff.setVisibility(View.VISIBLE);

        //0 if the argument Date is equal to this Date;
        // a value less than 0 if this Date is before the Date argument;
        // and a value greater than 0 if this Date is after the Date argument.

        if (diff < 0 )
        tvDiff.setText("Date 2 is greater than Date 1");
        else if (diff > 0)
        tvDiff.setText("Date 1 is greater than Date 2");
        else
            tvDiff.setText("Date 1 is equal to Date 2");
    }
}
