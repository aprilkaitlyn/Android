package com.example.orientationv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//only one XML file for both orientations
public final static int SPACING_VERTICAL = 50;
public final static int SPACING_HORIZONTAL = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration config = getResources().getConfiguration();
        modifyLayout(config);
    }

    private void modifyLayout(Configuration newConfig) {
        Button b2 = findViewById(R.id.btn2);
        ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) b2.getLayoutParams();
        Button b3 = findViewById(R.id.btn3);
        ViewGroup.MarginLayoutParams params3 = (ViewGroup.MarginLayoutParams) b3.getLayoutParams();

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            params2.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
            params3.setMargins( 0, SPACING_HORIZONTAL, 0, 0 );
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ) {
            params2.setMargins( 0, SPACING_VERTICAL, 0, 0 );
            params3.setMargins( 0, SPACING_VERTICAL, 0, 0 );
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig);
    }
}
