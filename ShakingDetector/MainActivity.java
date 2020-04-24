package com.example.shakingdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private ShakeDetector mShakeDetector;

    private static final String Tag = "MyActivity"; //for logging

    MagicAnswer mMagicAnswer;
    private TextView mAnswerTV;
    private EditText mQuestionET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references to layout elements
        mQuestionET = findViewById(R.id.EDIT_question);
        mAnswerTV = findViewById(R.id.TXT_answer);
        mMagicAnswer = new MagicAnswer(MainActivity.this);

        //register sensor manager & setup shake detection
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //cast as SensorManager
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //Sensor. brings up all the kinds of sensors
        mShakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                Log.d(Tag,"inside of onShake : shaking!!!");
                displayMagicAnswer();
            }
        });
    }

    private void displayMagicAnswer() { //once shake has been registered
        String magicAnswer = mMagicAnswer.getRandomAnswer();
        mAnswerTV.setText(magicAnswer); //display answer
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mSensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
