package com.ragnar.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.request.ApiClient;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inquilino> inquilinoMutable;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Inquilino> getInquilinoMutable() {
        if(inquilinoMutable == null) {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }
    public void obtenerInquilino(Bundle bundle){
        inquilinoMutable.setValue((Inquilino) bundle.getSerializable("inquilino"));

    }
    // TODO: Implement the ViewModel
}