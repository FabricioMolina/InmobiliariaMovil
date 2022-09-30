package com.ragnar.inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.modelo.Propietario;
import com.ragnar.inmobiliaria.request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> mutableInmueble;
    private ApiClient ap;
    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        ap = ApiClient.getApi();
    }

    public void iniciarSesion(String email, String pass){
        Propietario p = ap.login(email, pass);
        if(p != null){
            Intent i = new Intent(context, MenuActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else{
            Log.d("Salida", "No ingreso");
        }
    }
}
