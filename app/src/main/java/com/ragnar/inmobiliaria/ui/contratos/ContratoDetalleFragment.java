package com.ragnar.inmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ragnar.inmobiliaria.R;
import com.ragnar.inmobiliaria.modelo.Contrato;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel mViewModel;
    private TextView tvIdContratoDetalle,tvFechaInicioDetalle,tvFechaExpiracionDetalle,tvMontoDetalle,tvNombreInquilinoContratoDetalle,tvInmuebleContratoDetalle;
    private Button btnInquilinoDetalle, btnInmuebleDetalla;
    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        View view = inflater.inflate(R.layout.fragment_contrato_detalle, container, false);

        mViewModel.getMutableContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                tvIdContratoDetalle.setText(contrato.getIdContrato() + "");
                tvFechaInicioDetalle.setText(contrato.getFechaFin());
                tvFechaExpiracionDetalle.setText(contrato.getFechaFin());
                tvMontoDetalle.setText(contrato.getMontoAlquiler() + "");
                tvNombreInquilinoContratoDetalle.setText(contrato.getInquilino().getNombre());
                tvInmuebleContratoDetalle.setText(contrato.getInmueble().getDireccion());
                btnInquilinoDetalle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("inquilino", contrato.getInquilino()) ;
                        Navigation.findNavController(view).navigate(R.id.nav_inquilinoDetalleFragment, bundle);
                    }
                });
                btnInmuebleDetalla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("inmueble", contrato.getInmueble()) ;
                        Navigation.findNavController(view).navigate(R.id.nav_inmuebleDetalle, bundle);
                    }
                });
            }
        });
        inicializarVista(view);

        return view;
    }
    private void inicializarVista(View view) {
        tvIdContratoDetalle = view.findViewById(R.id.tvIdContratoDetalle);
        tvFechaInicioDetalle= view.findViewById(R.id.tvFechaInicioDetalle);
        tvFechaExpiracionDetalle= view.findViewById(R.id.tvFechaExpiracionDetalle);
        tvMontoDetalle= view.findViewById(R.id.tvMontoDetalle);
        tvNombreInquilinoContratoDetalle= view.findViewById(R.id.tvNombreInquilinoContratoDetalle);
        tvInmuebleContratoDetalle= view.findViewById(R.id.tvInmuebleContratoDetalle);
        btnInquilinoDetalle = view.findViewById(R.id.btnInquilinoDetalle);
        btnInmuebleDetalla = view.findViewById(R.id.btnInmuebleDetalle);


        mViewModel.obtenerInmuebles(getArguments());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}