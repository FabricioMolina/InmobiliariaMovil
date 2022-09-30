package com.ragnar.inmobiliaria.ui.logout;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Inmueble;

import java.util.ArrayList;

public class LogoutViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> decisionMutable;
    public LogoutViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }
    public LiveData<Boolean> getDecisionMutable(){
        if (decisionMutable == null) {
            decisionMutable = new MutableLiveData<>();
        }
        return decisionMutable;
    }
    public void muestraDialog(View view){

        new AlertDialog.Builder(view.getContext())
                .setTitle("Cerrar sesión")
                .setMessage("Seguro desea cerrar la sesión?")
                .setPositiveButton("Aceptar",new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface di,int i){
                        decisionMutable.setValue(true);

                    }
                })

                .setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface di,int i){
                        decisionMutable.setValue(false);
                        Navigation.findNavController(view).navigate(R.id.nav_home);
                    }
                }).show();


    }


    // TODO: Implement the ViewModel
}