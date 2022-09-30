package com.ragnar.inmobiliaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ragnar.inmobiliaria.modelo.Propietario;
import com.ragnar.inmobiliaria.request.ApiClient;
import com.squareup.seismic.ShakeDetector;

import java.util.List;

public class LoginActivity extends AppCompatActivity/*implements ShakeDetector.Listener*/{
    private TextView etEmail;
    private TextView etPass;
    private TextView btnIngresar;
    private LoginViewModel mv;
    private SensorManager sm;
    private int contador = -1;
    private SensorLogin sensorLogin;
    // private ShakeDetector sd = new ShakeDetector(this);




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        pedirPermisos();


        sensorLogin = new SensorLogin(getApplicationContext());
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size() > 0) {
            sm.registerListener(sensorLogin,list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        };

        // d.start(sm);*/


        inicialiazarVista();


    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(sensorLogin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size() > 0) {
            sm.registerListener(sensorLogin,list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        };
    }

    private void pedirPermisos() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},1000);
        }
    }

    private void inicialiazarVista() {

        etEmail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mv.iniciarSesion(etEmail.getText().toString(), etPass.getText().toString());
            }
        });


    }



    /*public void hearShake() {
        contador++;
        if(contador == 1){
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:2664846324"));
            startActivity(i);
            contador = -1;
        }

    }*/
}