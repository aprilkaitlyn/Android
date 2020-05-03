package com.example.fibonacchiflowerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> allPetals;
    private LayoutInflater layoutInflator;
    private Button pinkButton;
    private Button blueButton;
    private Button clearButton;
    private RelativeLayout relativeLayout;
    Flower myFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFlower = new Flower();
        allPetals = new ArrayList<>();

        //initialize generation of fibonacchi pattern
        initialize();

        //create layout inflator to add petals
        layoutInflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        relativeLayout = findViewById(R.id.relativeLayout);
        pinkButton = findViewById(R.id.BTN_pink);
        pinkButton.setOnClickListener(addPetal); //declared below
        blueButton = findViewById(R.id.BTN_blue);
        blueButton.setOnClickListener(addPetal);
        clearButton = findViewById(R.id.BTN_clear);
        clearButton.setOnClickListener(clearPetals); //declared below

        //set center coordinate
        DisplayMetrics metrics = new DisplayMetrics(); //pass this
        getWindowManager().getDefaultDisplay().getMetrics(metrics); //in here
        myFlower.set_xCenter(metrics.widthPixels/2);
        myFlower.set_yCenter(metrics.heightPixels/2-200);
    }

    private View.OnClickListener clearPetals = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        //remove all petals from layout
            for (int i = 0; i < allPetals.size(); i++) {
                ImageView petal = allPetals.get(i);
                relativeLayout.removeView(petal);
            }
        // reset variables to start over
            allPetals.clear();
            initialize(); //start over
        }
    };

    private View.OnClickListener addPetal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //grab the right petal -- pink or blue??
            String buttonText = ((Button)v).getText().toString(); //get button's text from the view
            ImageView petal;

            if (buttonText.equals("Add Pink"))
                petal = (ImageView) layoutInflator.inflate(R.layout.petal_a,null); //pink
            else
                petal = (ImageView) layoutInflator.inflate(R.layout.petal_b, null); //blue

            //set petal's visual properties
            petal.setX(myFlower.get_xCenter());
            petal.setY(myFlower.get_yCenter());
            petal.setPivotX(0);
            petal.setPivotY(100);
            petal.setScaleX(myFlower.getScaleX());
            petal.setScaleY(myFlower.getScaleY());
            petal.setRotation(myFlower.getRotate());

            //place inflated image in main layout
            relativeLayout.addView(petal, 0);

            //add image to array list
            allPetals.add(petal);

            //update
            myFlower.updatePetalValues();

        }
    };

    private void initialize() {
        myFlower.setRotate(0);
        myFlower.setScaleX((float)0.3);
        myFlower.setScaleY((float)0.3);
        myFlower.setDegenerate((float)1.001);
        myFlower.initializeAngle();
    }
}
