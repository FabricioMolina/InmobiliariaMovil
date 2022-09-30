package com.ragnar.inmobiliaria.ui.pagos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Pago;
import com.ragnar.inmobiliaria.ui.inquilinos.AdapterInquilino;

import java.util.ArrayList;

public class PagoFragment extends Fragment {

    private PagoViewModel mViewModel;
    private RecyclerView rv;
    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        View view = inflater.inflate(R.layout.fragment_pago, container, false);
        inicializarVista(view);


        mViewModel.getPagoMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                AdapterPago par = new AdapterPago(getContext(), getLayoutInflater() ,pagos);
                if(par.getItemCount() == 0){
                    Toast.makeText(getContext(), "Este Propietario carece de pagos asignados a sus contratos.", Toast.LENGTH_SHORT).show();
                }
                rv.setAdapter(par);
            }
        });
        mViewModel.traerPagos();


        return view;
    }
    private void inicializarVista(View view) {
        rv = view.findViewById(R.id.rvIdPago);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        // TODO: Use the ViewModel
    }

}