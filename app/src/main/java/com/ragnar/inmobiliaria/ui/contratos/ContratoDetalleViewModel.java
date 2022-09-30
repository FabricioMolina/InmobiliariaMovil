package com.ragnar.inmobiliaria.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Contrato;
import com.ragnar.inmobiliaria.modelo.Inmueble;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private Context context;
    private  MutableLiveData<Contrato> contratoMutable;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<Contrato> getMutableContrato() {
        if(contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }
    public void obtenerInmuebles(Bundle bundle){
        contratoMutable.setValue((Contrato) bundle.getSerializable("contrato"));

    }
    // TODO: Implement the ViewModel
}