package com.example.mortgagecalc2;

import java.text.DecimalFormat;

public class Mortgage {
    public static final DecimalFormat MONEY
            = new DecimalFormat( "$#,##0.00" );

    private static float amount;
    private static int years;
    private static float rate;

    public Mortgage( ) {
        setAmount( 100000.0f );
        setYears( 30 );
        setRate( 0.035f );
    }

    public void setAmount( float newAmount ) {
        if( newAmount >= 0 )
            amount = newAmount;
    }

    public void setYears( int newYears ) {
        if( newYears >= 0 )
            years = newYears;
    }

    public void setRate( float newRate ) {
        if( newRate >= 0 )
            rate = newRate;
    }

    public float getAmount( ) {
        return amount;
    }

    public static String getFormattedAmount() {
        return MONEY.format( amount );
    }

    public static int getYears() {
        return years;
    }

    public static float getRate() {
        return rate;
    }

    public static float monthlyPayment() {
        float mRate = rate / 12;  // monthly interest rate
        double temp = Math.pow( 1/( 1 + mRate ), years * 12 );
        return amount * mRate / ( float ) ( 1 - temp );
    }

    public static String formattedMonthlyPayment() {
        return MONEY.format( monthlyPayment( ) );
    }

    public static float totalPayment() {
        return monthlyPayment( ) * years * 12;
    }

    public static String formattedTotalPayment() {
        return MONEY.format( totalPayment( ) );
    }
}
