package com.example.orientationv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

//this version detects orientation and switches between XML layouts accordingly

    private static final String MA = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(MA, "Inside OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration config = getResources().getConfiguration();
        modifyLayout(config);
    }


    private void modifyLayout(Configuration newConfig) { //is it portrait or landscape? then changes to appropriate XML
        Log.w("MainActivity", "Inside modifyLayout");
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_main_landscape); //we create new XML for this layout
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Log.w("MainActivity", "Inside onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig); //if orientation changes again, send back to modifyLayout
    }

}
