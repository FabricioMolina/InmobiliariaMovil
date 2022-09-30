package com.ragnar.inmobiliaria.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.modelo.Contrato;
import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<ArrayList<Contrato>> contratoMutableLiveData;
    private MutableLiveData<Inquilino> inquilinoMutableLiveData;

    private ArrayList<Inmueble> inmuebles = ap.obtnerPropiedades();
    private ArrayList<Contrato> contratos = new ArrayList<Contrato>();

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Contrato>> getContratoMutableLiveData() {
        if (contratoMutableLiveData == null) {
            contratoMutableLiveData = new MutableLiveData<>();
        }
        return contratoMutableLiveData;
    }
    public LiveData<Inquilino> getInquilinoMutableLiveData() {
        if (inquilinoMutableLiveData == null) {
            inquilinoMutableLiveData = new MutableLiveData<>();
        }
        return inquilinoMutableLiveData;
    }


    public void traerContratos(){
        for (Inmueble i : inmuebles) {
            Contrato contrato = ap.obtenerContratoVigente(i);
            if(contrato != null){
                contratos.add(contrato);
                contratoMutableLiveData.setValue(contratos);
            }else{
                contratoMutableLiveData.setValue(contratos);
            }
        }

    }
    public void vaciarContratos(){
        contratos.clear();
    }

    // TODO: Implement the ViewModel
}