package com.ragnar.inmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Propietario;
import com.ragnar.inmobiliaria.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient ac;
    private MutableLiveData<Propietario>mutablePropietario;
    private MutableLiveData<Boolean>mutableBoolean;
    private MutableLiveData<String>mutableString;


    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<Propietario> getMutablePropietario() {
        if (mutablePropietario == null) {
            mutablePropietario = new MutableLiveData<>();
        }
        return mutablePropietario;
    }
    public LiveData<Boolean> getMutableBoolean() {
        if (mutableBoolean == null) {
            mutableBoolean = new MutableLiveData<>();
        }
        return mutableBoolean;
    }
    public LiveData<String> getMutableString() {
        if (mutableString == null) {
            mutableString = new MutableLiveData<>();
        }
        return mutableString;
    }

    public void traerUser() {
        ac = ApiClient.getApi();
        Propietario resultado = ac.obtenerUsuarioActual();

        mutablePropietario.setValue(resultado);
    }
    public void modificar(String btn, Propietario p){
        ac = ApiClient.getApi();

        switch (btn.toUpperCase()){
            case "MODIFICAR":
                mutableString.setValue("Actualizar");
                mutableBoolean.setValue(true);
                break;
            case "ACTUALIZAR":
                ac.actualizarPerfil(p);
                mutablePropietario.setValue(p);
                mutableString.setValue("Modificar");
                mutableBoolean.setValue(false);
                break;
        }
    }
    public void actualizar(){

    }


    // TODO: Implement the ViewModel
}