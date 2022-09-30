package com.ragnar.inmobiliaria;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.Uri;
import android.util.Log;

public class SensorLogin implements SensorEventListener {

    private Context context;

    public SensorLogin(Context context) {

        this.context = context;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] >= 40 ) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:2664846324"));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



}
