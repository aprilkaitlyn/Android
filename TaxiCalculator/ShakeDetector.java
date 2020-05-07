package com.example.TaxiCalculator;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

class ShakeDetector implements SensorEventListener {

    private long mTimeOfLastShake;
    private static final float SHAKE_THRESHOLD = 25f; //make sure enough movement is there to call it a shake
    private static final int SHAKE_TIMELAPSE = 200; //2 seconds of shaking
    private static final String Tag = "MyActivity"; //for logging

    private OnShakeListener mShakeListener;

    public ShakeDetector(OnShakeListener shakeListener) {
        mShakeListener = shakeListener;
    }
    
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) { //auto-implemented
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            //collect sensor values on 3 axis - where it is before it moves
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            //convert each accelerometer measurement into a g-force measurement by neutralizing gravity - where it is after it moves
            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            //compute g-force as a directionless measurement (g-force is approx 1 when not shaking)
            double vector = Math.pow(gForceX, 2.0) + Math.pow(gForceY, 2.0) + Math.pow(gForceZ, 2.0);
            float gForce = (float) Math.sqrt(vector);

            //determine if g-force is enough to register as a shake
            if (gForce > SHAKE_THRESHOLD) {

                final long now = System.currentTimeMillis();
                if (mTimeOfLastShake + SHAKE_TIMELAPSE > now) {
                    return;
                }
                mTimeOfLastShake = now;
                float speed = Math.abs(x + y + z - gForceX - gForceY - gForceZ) / mTimeOfLastShake * 10000;
                Log.d(Tag, "shake detected with speed:" + speed);

                //listener registered a shake
                mShakeListener.onShake();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { //auto-implemented
    }

    public interface OnShakeListener {
         void onShake();
    }
}
