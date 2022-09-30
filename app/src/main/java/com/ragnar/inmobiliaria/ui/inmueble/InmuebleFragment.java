package com.ragnar.inmobiliaria.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Inmueble;
import com.ragnar.inmobiliaria.request.ApiClient;
import com.ragnar.inmobiliaria.ui.inquilinos.InquilinoViewModel;

import java.util.ArrayList;

public class InmuebleFragment extends Fragment {

    public static InmuebleFragment newInstance() {
        return new InmuebleFragment();
    }
    private ApiClient ap = ApiClient.getApi();
    private InmuebleViewModel mViewModel;
    private RecyclerView rv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ArrayList<Inmueble> inmuebles = ap.obtnerPropiedades();

        View view = inflater.inflate(R.layout.fragment_inmueble, container, false);
        mViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        inicializarVista(view);
        mViewModel.getListInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {

                    AdapterInmueble par = new AdapterInmueble(view.getContext(), inmuebles, getLayoutInflater());
                    if(par.getItemCount() == 0){
                        Toast.makeText(getContext(), "Este Propietario carece de Inmuebles.", Toast.LENGTH_SHORT).show();
                    }
                    rv.setAdapter(par);


            }
        });
        mViewModel.trarInmuebles();





        return view;

    }

    public void inicializarVista(View view) {
        rv = view.findViewById(R.id.rvId);
        rv.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        // TODO: Use the ViewModel
    }

}