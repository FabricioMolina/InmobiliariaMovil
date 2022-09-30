package com.ragnar.inmobiliaria;

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

public class MenuViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient ac;
    private MutableLiveData<Propietario>mutablePropietario;
    private MutableLiveData<Boolean>mutableBoolean;
    private MutableLiveData<String>mutableString;


    public MenuViewModel(@NonNull Application application) {
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


    // TODO: Implement the ViewModel
}