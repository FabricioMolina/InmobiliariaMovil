package com.ragnar.inmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ragnar.inmobiliaria.LoginViewModel;
import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Contrato;
import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.ui.contratos.AdapterContrato;
import com.ragnar.inmobiliaria.ui.perfil.PerfilViewModel;

import java.util.ArrayList;

public class ContratoFragment extends Fragment {

    private ContratoViewModel mViewModel;
    private RecyclerView rv;
    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contrato, container, false);

        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

        inicializarVista(view);


        mViewModel.getContratoMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contrato>>() {
            @Override
            public void onChanged(ArrayList<Contrato> contratoes) {
                    AdapterContrato par = new AdapterContrato(getContext(), getLayoutInflater() ,contratoes);
                        if(par.getItemCount() == 0){
                            Toast.makeText(getContext(), "Este Propietario carece de inmuebles con contratos.", Toast.LENGTH_SHORT).show();
                        }
                    rv.setAdapter(par);

            }
        });
        mViewModel.traerContratos();



        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.vaciarContratos();
    }

    private void inicializarVista(View view) {

        rv = view.findViewById(R.id.rvIdContato);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}