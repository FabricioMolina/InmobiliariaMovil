package com.ragnar.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class InquilinoViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient ap = ApiClient.getApi();
    private MutableLiveData<ArrayList<Inquilino>> listMutableLiveData;
    private ArrayList<Inmueble> inmuebles = ap.obtnerPropiedades();
    private ArrayList<Inquilino> inquilinos = new ArrayList<Inquilino>();

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<ArrayList<Inquilino>> getListMutableLiveData() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
        }
        return listMutableLiveData;
    }
    public void traerInquilinos(){

        for (Inmueble i : inmuebles) {
            if(ap.obtenerInquilino(i) != null){
                inquilinos.add(ap.obtenerInquilino(i));
            }
        }
        listMutableLiveData.setValue(inquilinos);
    }
    public void borrarInquilinos(){
        inquilinos.clear();
    }
    // TODO: Implement the ViewModel
}