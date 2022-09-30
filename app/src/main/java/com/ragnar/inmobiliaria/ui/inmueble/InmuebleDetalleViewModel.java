package com.ragnar.inmobiliaria.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.request.ApiClient;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient ap = ApiClient.getApi();

    private  MutableLiveData<Inmueble> inmuebleMutable;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Inmueble> getMutableInmueble() {
        if(inmuebleMutable == null) {
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }
    public void obtenerInmuebles(Bundle bundle){
        inmuebleMutable.setValue((Inmueble) bundle.getSerializable("inmueble"));

    }

    public void modificar(Inmueble inmueble) {
        ap.actualizarInmueble(inmueble);
    }


    // TODO: Implement the ViewModel
}