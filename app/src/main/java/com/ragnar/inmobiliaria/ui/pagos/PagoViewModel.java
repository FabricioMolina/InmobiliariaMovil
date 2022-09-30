package com.ragnar.inmobiliaria.ui.pagos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Contrato;
import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.modelo.Pago;
import com.ragnar.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class PagoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Pago>> pagoMutable;
    private ApiClient ap = ApiClient.getApi();

    private ArrayList<Inmueble> inmuebles = ap.obtnerPropiedades();
    private ArrayList<Contrato> contratos = new ArrayList<Contrato>();
    private ArrayList<Pago> pagos = new ArrayList<Pago>();


    public PagoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<ArrayList<Pago>> getPagoMutable(){
        if(pagoMutable == null){
            pagoMutable = new MutableLiveData<>();
        }
        return pagoMutable;
    }


    public void traerPagos(){
        for (Inmueble i : inmuebles) {
            Contrato contrato = ap.obtenerContratoVigente(i);
            if(contrato != null){
                contratos.add(contrato);
            }
        }
        if(contratos.size() > 0){
            for (Contrato c : contratos) {
                pagos = ap.obtenerPagos(c);
                if(pagos != null){
                    pagoMutable.setValue(pagos);
                }
            }
        }else{
            pagoMutable.setValue(pagos);
        }

    }

    // TODO: Implement the ViewModel
}