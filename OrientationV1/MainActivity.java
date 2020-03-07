package com.example.orientationv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;


//this version still doesn't change the emulator, just adds to the LogCat
//we added a line to the manifest so the LogCat will "watch" for orientation change

public class MainActivity extends AppCompatActivity {

    private static final String MA = "MainActivity"; //MA is the "tag" for the log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.w(MA, "height: " + newConfig.screenHeightDp);
        Log.w(MA, "width: " + newConfig.screenWidthDp);

        Log.w(MA, "Orientation: " + newConfig.orientation);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            Log.w(MA, "Horizontal position");
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            Log.w(MA, "Vertical position");
        else
            Log.w(MA, "Undetermined position");
    }
}
