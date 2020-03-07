package com.example.orientationv0;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.res.Configuration;
        import android.content.res.Resources;
        import android.graphics.Point;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.util.Log;


//this version doesn't change the emulator, just adds to the LogCat

public class MainActivity extends AppCompatActivity {

    private static final String MA = "MainActivity"; //MA is the "tag" for the log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration config = getResources().getConfiguration();
        Log.w(MA, "screen dp height:  " + config.screenHeightDp); //we use log to display messages in LogCat
        Log.w(MA, "screen dp width: " + config.screenWidthDp ); //we made a "MA" filter in LogCat for these

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        Log.w(MA, "screen height in pixels = " + screenHeight);
        Log.w(MA, "screen width in pixels = " + screenWidth);

        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        float pixelDensity = metrics.density;
        Log.w(MA, "logical pixel density = " + pixelDensity);

        if(config.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_XLARGE)) //determine what size of screen
            Log.w(MA, "Extra large size screen");
        else if(config.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_LARGE))
            Log.w(MA, "Large size screen" );
        else if(config.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_NORMAL))
            Log.w(MA, "Normal size screen"); //my emulator Pixel is normal size
        else if(config.isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_SMALL))
            Log.w(MA, "Small size screen");
        else
            Log.w(MA, "Unknown size screen");

        Log.w(MA, "Landscape constant: " + Configuration.ORIENTATION_LANDSCAPE);
        Log.w(MA, "Portrait constant: " + Configuration.ORIENTATION_PORTRAIT);
        Log.w(MA, "Orientation: " + config.orientation);
        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE)
            Log.w(MA, "Horizontal position");
        else if(config.orientation == Configuration.ORIENTATION_PORTRAIT)
            Log.w(MA, "Vertical position");
        else
            Log.w(MA, "Undetermined position");
    }
}

//with emulator open, go to its settings and turn on auto-rotate//
