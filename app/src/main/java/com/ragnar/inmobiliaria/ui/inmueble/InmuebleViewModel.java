package com.ragnar.inmobiliaria.ui.inmueble;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class InmuebleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> listInmuebleMutable;
    private ApiClient ap = ApiClient.getApi();
    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

    }
    public LiveData<ArrayList<Inmueble>> getListInmuebleMutable(){
        if (listInmuebleMutable == null) {
            listInmuebleMutable = new MutableLiveData<>();
        }
        return listInmuebleMutable;
    }
    public void trarInmuebles(){
        listInmuebleMutable.setValue(ap.obtnerPropiedades());
    }
    // TODO: Implement the ViewModel
}