package com.ragnar.inmobiliaria.ui.inquilinos;

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
import com.ragnar.inmobiliaria.modelo.Inquilino;
import com.ragnar.inmobiliaria.request.ApiClient;
import com.ragnar.inmobiliaria.ui.inmueble.AdapterInmueble;
import com.ragnar.inmobiliaria.ui.inmueble.InmuebleViewModel;

import java.util.ArrayList;
import java.util.List;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel mViewModel;

    private RecyclerView rv;
    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inquilino, container, false);
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        inicializarVista(view);

        mViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inquilino>>() {
            @Override
            public void onChanged(ArrayList<Inquilino> inquilinos) {

                AdapterInquilino par = new AdapterInquilino(getContext(), getLayoutInflater() ,inquilinos);
                if(par.getItemCount() == 0){
                    Toast.makeText(getContext(), "Este Propietario carece de inmuebles con inquilinos.", Toast.LENGTH_SHORT).show();
                }
                rv.setAdapter(par);
            }
        });
        mViewModel.traerInquilinos();

        //if(inquilinos.size() != 0 && inquilinos != null){
        return view;
    }

    private void inicializarVista(View view) {
        rv = view.findViewById(R.id.rvIdInquilinos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.borrarInquilinos();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}