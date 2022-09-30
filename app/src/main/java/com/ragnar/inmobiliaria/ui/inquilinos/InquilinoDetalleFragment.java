package com.ragnar.inmobiliaria.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Inquilino;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel mViewModel;
    private TextView tvDniInquilinoDetalle, tvNombreInquilinoDetalle,tvEmailInquilinoDetalle,tvTelefonoInquilinoDetalle,tvTelefonoGaranteDetalle,tvNombreGaranteDetalle, tvLugarDeTrabajoDetalle;
    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inquilino_detalle, container, false);
        mViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        mViewModel.getInquilinoMutable().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvDniInquilinoDetalle.setText(inquilino.getDNI() + "");
                tvNombreInquilinoDetalle.setText(inquilino.getNombre() +" "+ inquilino.getApellido());
                tvEmailInquilinoDetalle.setText(inquilino.getEmail());
                tvTelefonoInquilinoDetalle.setText(inquilino.getTelefono());
                tvNombreGaranteDetalle.setText(inquilino.getNombreGarante());
                tvLugarDeTrabajoDetalle.setText(inquilino.getLugarDeTrabajo());
                tvTelefonoGaranteDetalle.setText(inquilino.getTelefonoGarante());
            }
        });
        inicializarVista(view);
        return view;
    }

    private void inicializarVista(View view) {
        tvDniInquilinoDetalle = view.findViewById(R.id.tvDniInquilinoDetalle);
        tvNombreInquilinoDetalle= view.findViewById(R.id.tvNombreInquilinoDetalle);
        tvEmailInquilinoDetalle= view.findViewById(R.id.tvEmailInquilinoDetalle);
        tvTelefonoInquilinoDetalle= view.findViewById(R.id.tvTelefonoInquilinoDetalle);
        tvNombreGaranteDetalle= view.findViewById(R.id.tvNombreGaranteDetalle);
        tvTelefonoGaranteDetalle= view.findViewById(R.id.tvTelefonoGaranteDetalle);
        tvLugarDeTrabajoDetalle = view.findViewById(R.id.tvLugarDeTrabajoInquilinoDetalle);
        mViewModel.obtenerInquilino(getArguments());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}