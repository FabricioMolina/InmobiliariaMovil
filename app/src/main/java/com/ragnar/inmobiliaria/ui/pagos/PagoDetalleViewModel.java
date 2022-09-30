package com.ragnar.inmobiliaria.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.modelo.Pago;

public class PagoDetalleViewModel extends AndroidViewModel {
    private Context contex;
    private MutableLiveData<Pago> pagoMutable;
    public PagoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.contex = application.getApplicationContext();
    }
    public LiveData<Pago> getPagoMutable() {
        if(pagoMutable == null) {
            pagoMutable = new MutableLiveData<>();
        }
        return pagoMutable;
    }
    public void obtenerPago(Bundle bundle){
        pagoMutable.setValue((Pago) bundle.getSerializable("pago"));
    }

    // TODO: Implement the ViewModel
}