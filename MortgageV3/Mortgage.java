package com.example.mortgagecalc3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.DecimalFormat;
import java.util.prefs.PreferenceChangeEvent;

public class Mortgage {
    public static final DecimalFormat MONEY = new DecimalFormat( "$#,##0.00" );
    private static final String PREFERENCE_AMOUNT = "amount";
    private static final String PREFERENCE_YEARS = "years";
    private static final String PREFERENCE_RATE = "rate";

    private static float amount;
    private static int years;
    private static float rate;

    public Mortgage() {
        setAmount( 100000.0f );
        setYears( 30 );
        setRate( 0.035f );
    }

    public Mortgage(Context context) { //sets our default numbers ??? see more at bottom
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setAmount(pref.getFloat(PREFERENCE_AMOUNT, amount));
        setYears(pref.getInt(PREFERENCE_YEARS, years));
        setRate(pref.getFloat(PREFERENCE_RATE, rate));
    }

    public void setAmount(float newAmount) {
        if(newAmount >= 0)
            amount = newAmount;
    }

    public void setYears(int newYears) {
        if(newYears >= 0)
            years = newYears;
    }

    public void setRate(float newRate) {
        if(newRate >= 0)
            rate = newRate;
    }

    public float getAmount() {
        return amount;
    }

    public static String getFormattedAmount() {
        return MONEY.format(amount);
    }

    public static int getYears() {
        return years;
    }

    public static float getRate() {
        return rate;
    }

    public static float monthlyPayment() {
        float mRate = rate / 12;  // monthly interest rate
        double temp = Math.pow( 1/(1 + mRate), years * 12);
        return amount * mRate /  (float) (1 - temp);
    }

    public static String formattedMonthlyPayment() {
        return MONEY.format(monthlyPayment());
    }

    public static float totalPayment() {
        return monthlyPayment() * years * 12;
    }

    public static String formattedTotalPayment() {
        return MONEY.format(totalPayment());
    }

    //write mortgage data to preferences - makes an XML file to "persist on the device"
    public void setPreferences(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(PREFERENCE_AMOUNT, amount); //put data type and then variable name
        editor.putInt(PREFERENCE_YEARS, years);
        editor.putFloat(PREFERENCE_RATE, rate);
        editor.commit(); //don't forget
    }


}
