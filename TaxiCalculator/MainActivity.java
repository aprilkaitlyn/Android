package com.example.TaxiCalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Taxi taxi;
    DatabaseManager dbManager;

    EditText miles;
    TextView total;
    TextView base;
    TextView per;

    //ShakeDetector
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private ShakeDetector mShakeDetector;
    private static final String Tag = "MyActivity"; //for logging


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taxi = new Taxi(0, null, 0, 0); //Taxi.java
        dbManager = new DatabaseManager(this); //DatabaseManager.java


        //FragmentManager gives the tools for working with fragments
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.FRAG_info) == null) { //checking that fragment exists
            FragmentTransaction ft = fm.beginTransaction();
            EnterInfoFragment fragment = new EnterInfoFragment(); //enter info fragment
            ft.add(R.id.FRAG_info, fragment);
            ft.commit(); //need this for fragment to be visible
        }

        if (fm.findFragmentById(R.id.FRAG_database) == null) { //checking that fragment exists
            FragmentTransaction ft = fm.beginTransaction();
            DatabaseFragment fragment = new DatabaseFragment(); //database fragment
            ft.add(R.id.FRAG_database, fragment);
            ft.commit(); //need this for fragment to be visible
        }

        //register sensor manager & setup shake detection
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //cast as SensorManager
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //Sensor. brings up all the kinds of sensors
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                Log.d(Tag,"inside of onShake : shaking!!!");
                clearFields(); //see below
            }
        });
    }

    private void clearFields() { //clear the EditTexts once shake has been registered
        miles = findViewById(R.id.EDIT_Miles);
        total = findViewById(R.id.TXT_TotalCost);
        base = findViewById(R.id.TXT_BasePrice);
        per = findViewById(R.id.TXT_PricePerMile);

        miles.setText("");
        per.setText("");
        base.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mSensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu (tells Android about the menu we created)
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //handle aciton bar items here
        int id = item.getItemId(); // lets us use our menu buttons in a switch statement

        switch(id) {
            case R.id.action_add:
                Log.w("MainActivity", "add selected");
                Intent insertIntent = new Intent(this, InsertActivity.class); //InsertActivity
                //add the activity to the manifest!!!
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "delete selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class); //DeleteActivity
                this.startActivity(deleteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void calculate(View view) {
        double milesMath;
        double baseMath;
        double perMath;
        double totalCost;

        miles = findViewById(R.id.EDIT_Miles);
        total = findViewById(R.id.TXT_TotalCost);
        base = findViewById(R.id.TXT_BasePrice);
        per = findViewById(R.id.TXT_PricePerMile);

        milesMath = Double.parseDouble(miles.getText().toString());
        baseMath = Double.parseDouble(base.getText().toString());
        perMath = Double.parseDouble(per.getText().toString());

        totalCost = (milesMath * perMath + baseMath); //equation

        total.setText("$" + totalCost);
        }
    }
